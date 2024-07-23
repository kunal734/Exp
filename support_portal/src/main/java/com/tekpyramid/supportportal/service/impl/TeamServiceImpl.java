package com.tekpyramid.supportportal.service.impl;

import com.tekpyramid.supportportal.dao.TeamDao;
import com.tekpyramid.supportportal.dao.UserDao;
import com.tekpyramid.supportportal.data.models.dto.ApiResponse;
import com.tekpyramid.supportportal.data.models.dto.TeamDto;
import com.tekpyramid.supportportal.data.models.entity.Team;
import com.tekpyramid.supportportal.data.models.entity.User;
import com.tekpyramid.supportportal.data.models.enums.Role;
import com.tekpyramid.supportportal.service.TeamService;
import com.tekpyramid.supportportal.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamDao teamDao;

    private final ModelMapper modelMapper;

    private final UserDao userDao;

    private final MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<ApiResponse> saveTeam(TeamDto teamDto, String adminEmail) {
        Team team = modelMapper.map(teamDto, Team.class);
        Optional<User> optionalAdmin = userDao.findUserByEmail(adminEmail);
        if(optionalAdmin.isPresent() && optionalAdmin.get().getRole().equals(Role.ADMIN)){
            team.createEntity(optionalAdmin.get().getName() ,adminEmail);
            return ResponseUtil.getCreatedResponse(teamDao.saveTeam(team));
        }
        return ResponseUtil.getBadRequestResponse("You are not an admin");
    }

    @Override
    public ResponseEntity<ApiResponse> getTeamNames() {
        return ResponseUtil.getOkResponse(teamDao.getTeamNames());
    }

    @Override
    public ResponseEntity<ApiResponse> getAllTeams(int page, int size) {
        Page<Team> teams = teamDao.getAllTeams(page, size);
        GroupOperation groupOperation = Aggregation.group("team").count().as("number");
        Aggregation aggregation = Aggregation.newAggregation(groupOperation);
        List<User> teamList = mongoTemplate.aggregate(aggregation, User.class, User.class).getMappedResults();
        System.out.println(teamList);
        return ResponseUtil.getOkResponse(teams);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteTeam(String team) {
        Optional<Team> teamEntity = teamDao.findByName(team);
        if(teamEntity.isPresent()){
            Optional<Page<User>> users = userDao.findUserByTeam(team,0,1);
            if(users.isPresent() && users.get().getTotalElements()==0){
                teamDao.deleteTeam(teamEntity.get());
                return ResponseUtil.getOkResponse("Team deleted successfully");
            }
            return ResponseUtil.getBadRequestResponse("Team has users");
        }
        return ResponseUtil.getOkResponse("Team not found");
    }
}