package com.tekpyramid.supportportal.service;

import com.tekpyramid.supportportal.data.models.dto.ApiResponse;
import com.tekpyramid.supportportal.data.models.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ApiResponse> saveUser(UserDto userDto, String email);

    ResponseEntity<ApiResponse> updateUserTeam(String adminEmail, String userEmail, String team);

    ResponseEntity<ApiResponse> getAllUsers(int page, int size);

    ResponseEntity<ApiResponse> getUserByEmail(String email);

    ResponseEntity<ApiResponse> getUserByName(String name);

    ResponseEntity<ApiResponse> findUserByTeam(String team, int page, int size);

    ResponseEntity<ApiResponse> getAllActiveUsers(int page, int size);

    ResponseEntity<ApiResponse> deleteUserByEmail(String email);

    ResponseEntity<ApiResponse> getUsersAssignedTo(int page, int size);

    ResponseEntity<ApiResponse> setPassword(String email, String password);

    ResponseEntity<ApiResponse> getUsersCreatedBy(String name, String email, int page, int size);

    ResponseEntity<ApiResponse> findByEmailAndPassword(String email, String password);

    ResponseEntity<ApiResponse> searchUsersToAssign(String searchQuery);

    ResponseEntity<ApiResponse> updateAccess(String adminEmail, String userEmail);

    ResponseEntity<ApiResponse> findActiveUsersByTeam(String team, int page, int size);
}
