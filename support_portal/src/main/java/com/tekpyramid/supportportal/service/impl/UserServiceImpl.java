package com.tekpyramid.supportportal.service.impl;

import com.tekpyramid.supportportal.dao.TeamDao;
import com.tekpyramid.supportportal.dao.TicketDao;
import com.tekpyramid.supportportal.dao.UserDao;
import com.tekpyramid.supportportal.data.models.dto.ApiResponse;
import com.tekpyramid.supportportal.data.models.dto.UserDto;
import com.tekpyramid.supportportal.data.models.entity.Team;
import com.tekpyramid.supportportal.data.models.entity.Ticket;
import com.tekpyramid.supportportal.data.models.entity.User;
import com.tekpyramid.supportportal.data.models.enums.Access;
import com.tekpyramid.supportportal.data.models.enums.Role;
import com.tekpyramid.supportportal.data.models.enums.Status;
import com.tekpyramid.supportportal.service.UserService;
import com.tekpyramid.supportportal.utils.ResponseUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final TeamDao teamDao;
    private final TicketDao ticketDao;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ApiResponse> saveUser(UserDto userDto, String adminEmail) {
        Optional<User> optionalAdmin = userDao.findUserByEmail(adminEmail);
        if(optionalAdmin.isPresent() && optionalAdmin.get().getRole().equals(Role.ADMIN)) {
            if(Objects.nonNull(userDto.getTeam())) {
                Optional<Team> teamOptional = teamDao.findByName(userDto.getTeam());
                if (teamOptional.isPresent()) {
                    Optional<User> optionalUser = userDao.findUserByEmail(userDto.getEmail());
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        user.setFields(Role.INTERNAL_TEAM, Status.ACTIVATION_PENDING, Access.VIEW_ACCESS);
                        optionalUser.get().setToken(UUID.randomUUID().toString());
                        return ResponseUtil.getCreatedResponse(userDao.saveUser(user));
                    }
                    User userEntity = modelMapper.map(userDto, User.class);
                    userEntity.setFields(Role.INTERNAL_TEAM, Status.ACTIVATION_PENDING, Access.VIEW_ACCESS);
                    userEntity.setToken(UUID.randomUUID().toString());
                    userEntity.createEntity(optionalAdmin.get().getName(), optionalAdmin.get().getEmail());
                    return ResponseUtil.getCreatedResponse(userDao.saveUser(userEntity));
                }
                return ResponseUtil.getBadRequestResponse("Team does not exist");
            }
            User user = modelMapper.map(userDto, User.class);
            user.createEntity(optionalAdmin.get().getName(), optionalAdmin.get().getEmail());
            user.setFields(Role.CUSTOMER, Status.ACTIVE, Access.FULL_ACCESS);
            return ResponseUtil.getCreatedResponse(userDao.saveUser(user));
        }
        return ResponseUtil.getBadRequestResponse("You are not authorised to create a new user");
    }

    @Override
    public ResponseEntity<ApiResponse> updateUserTeam(String adminEmail, String userEmail, String team) {
        Optional<User> optionalAdmin = userDao.findUserByEmail(adminEmail);
        if(optionalAdmin.isPresent() && optionalAdmin.get().getRole().equals(Role.ADMIN)){
            Optional<Team> teamOptional = teamDao.findByName(team);
            if (teamOptional.isPresent()) {
                Optional<User> optionalUser = userDao.findUserByEmail(userEmail);
                if(optionalUser.isPresent()) {
                    User userEntity = optionalUser.get();
                    userEntity.setNumberOfTicket(ticketDao.findCountOfTicketAssociatedWithUser(userEmail));
                    if(userEntity.getNumberOfTicket()==0) {
                        userEntity.modifyEntity(optionalAdmin.get().getName(), optionalAdmin.get().getEmail());
                        userEntity.setTeam(team);
                        return ResponseUtil.getCreatedResponse(userDao.saveUser(userEntity));
                    }
                    return ResponseUtil.getOkResponse(optionalUser.get().getName()+" has "+userEntity.getNumberOfTicket()+" ticket(s) associated with them. Please resolve the tickets before changing the team");
                }
                return ResponseUtil.getOkResponse("User not found with email: "+userEmail);
            }
            return ResponseUtil.getBadRequestResponse("Team does not exist");
        }
        return ResponseUtil.getBadRequestResponse("You are not authorised to update the team");
    }


    @Override
    public ResponseEntity<ApiResponse> getAllUsers(int page, int size) {
        return ResponseUtil.getOkResponse(userDao.getAllUsers(page, size));
    }

    @Override
    public ResponseEntity<ApiResponse> getUserByEmail(String email) {
        Optional<User> optionalUser = userDao.findUserByEmail(email);
        if(optionalUser.isPresent()) {
            return ResponseUtil.getOkResponse(optionalUser);
        }
        return ResponseUtil.getNoContentResponse("User not found");
    }

    @Override
    public ResponseEntity<ApiResponse> setPassword(String token, String password) {
        Optional<User> optionalUser = userDao.findByToken(token);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(Status.ACTIVE);
            user.setToken(null);
            user.setPassword(passwordEncoder.encode(password));
            user.modifyEntity(optionalUser.get().getName(), optionalUser.get().getEmail());
            return ResponseUtil.getOkResponse(userDao.saveUser(user));
        }
        return ResponseUtil.getBadRequestResponse("You need to create an account first");
    }

    @Override
    public ResponseEntity<ApiResponse> getUserByName(String name) {
        return ResponseUtil.getOkResponse(userDao.findUserByName(name));
    }

    @Override
    public ResponseEntity<ApiResponse> findUserByTeam(String team, int page, int size) {
        return ResponseUtil.getOkResponse(userDao.findUserByTeam(team, page , size));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllActiveUsers(int page, int size) {
        return ResponseUtil.getOkResponse(userDao.getAllActiveUsers(page, size));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteUserByEmail(String email) {
        Optional<User> user = userDao.findUserByEmail(email);
        if(user.isPresent()) {
            userDao.deleteUserByEmail(user.get());
            return ResponseUtil.getOkResponse("User deleted successfully");
        }
        return ResponseUtil.getOkResponse("User not present");
    }

    @Override
    public ResponseEntity<ApiResponse> getUsersAssignedTo(int page, int size) {
        List<Ticket> tickets = ticketDao.findAllTickets();
        List<User> userList = userDao.getAllUsers(page, size).stream().filter(user-> Objects.equals(user.getEmail(), tickets.stream().map(Ticket::getAssignedToEmail))).toList();
        return ResponseUtil.getOkResponse(userList);
    }

    @Override
    public ResponseEntity<ApiResponse> getUsersCreatedBy(String name, String email, int page, int size) {
        return ResponseUtil.getOkResponse(userDao.getUsersCreatedByNameOrEmail(name, email, page, size));
    }

    @Override
    public ResponseEntity<ApiResponse> findByEmailAndPassword(String email, String password) {
        return ResponseUtil.getOkResponse(userDao.findByEmailAndPassword(email, password));
    }


    @Override
    public ResponseEntity<ApiResponse> searchUsersToAssign(String searchQuery) {
        return ResponseUtil.getOkResponse(userDao.searchUsersToAssign(searchQuery));
    }

    @Override
    public ResponseEntity<ApiResponse> updateAccess(String email, String userEmail) {
        Optional<User> optional = userDao.findUserByEmail(email);
        if (optional.isPresent() && optional.get().getAccess().equals(Access.FULL_ACCESS)){
            Optional<User> optionalUser = userDao.findUserByEmail(userEmail);
            if (optionalUser.isPresent()){
                if(!optionalUser.get().getRole().equals(Role.ADMIN)) {
                    User user = optionalUser.get();
                    user.setAccess(user.getAccess() == Access.FULL_ACCESS ? Access.VIEW_ACCESS : Access.FULL_ACCESS);
                    user.modifyEntity(optional.get().getName(), optional.get().getEmail());
                    return ResponseUtil.getOkResponse(userDao.saveUser(user));
                }
                return ResponseUtil.getBadRequestResponse("You are not authorised to update admin access");
            }
            return ResponseUtil.getBadRequestResponse("User not found");
        }
        return ResponseUtil.getBadRequestResponse("You are not authorised to update access");
    }

    @Override
    public ResponseEntity<ApiResponse> findActiveUsersByTeam(String team, int page, int size) {
        return ResponseUtil.getOkResponse(userDao.findActiveUsersByTeam(team, page, size));
    }

}
