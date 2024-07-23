package com.tekpyramid.supportportal.service;

import com.tekpyramid.supportportal.data.models.dto.ApiResponse;
import com.tekpyramid.supportportal.data.models.dto.TeamDto;
import org.springframework.http.ResponseEntity;

public interface TeamService {
    ResponseEntity<ApiResponse> saveTeam(TeamDto teamDto, String adminEmail);

    ResponseEntity<ApiResponse> getTeamNames();

    ResponseEntity<ApiResponse> getAllTeams(int page, int size);

    ResponseEntity<ApiResponse> deleteTeam(String team);
}
