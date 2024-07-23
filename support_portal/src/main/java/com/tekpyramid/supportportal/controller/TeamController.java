package com.tekpyramid.supportportal.controller;

import com.tekpyramid.supportportal.data.models.dto.ApiResponse;
import com.tekpyramid.supportportal.data.models.dto.TeamDto;
import com.tekpyramid.supportportal.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("saveTeam")
    public ResponseEntity<ApiResponse> saveTeam(TeamDto teamDto, String adminEmail) {
        return teamService.saveTeam(teamDto, adminEmail);
    }

    @GetMapping("getTeamNames")
    public ResponseEntity<ApiResponse> getTeamNames() {
        return teamService.getTeamNames();
    }

    @GetMapping("getAllTeams")
    public ResponseEntity<ApiResponse> getAllTeams(int page, int size) {
        return teamService.getAllTeams(page, size);
    }

    @DeleteMapping("deleteTeam")
    public ResponseEntity<ApiResponse> deleteTeam(String team) {
        return teamService.deleteTeam(team);
    }

}
