package com.tekpyramid.supportportal.controller;

import com.tekpyramid.supportportal.data.models.dto.ApiResponse;
import com.tekpyramid.supportportal.data.models.dto.UserDto;
import com.tekpyramid.supportportal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("saveUser")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody @Valid UserDto userDto, @RequestHeader String email){
        return userService.saveUser(userDto, email);
    }

    @PutMapping("updateUserTeam")
    public ResponseEntity<ApiResponse> updateUserTeam(@RequestHeader String adminEmail, @RequestHeader String userEmail, @RequestHeader String team){
        return userService.updateUserTeam(adminEmail, userEmail, team);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<ApiResponse> getAllUsers(@RequestParam(defaultValue = "0", value = "page") int page, @RequestParam(defaultValue = "10", value = "size") int size){
        return userService.getAllUsers(page, size);
    }

    @GetMapping("getUserByEmail")
    public ResponseEntity<ApiResponse> getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @PatchMapping("setPassword")
    public ResponseEntity<ApiResponse> setPassword(@RequestHeader String token, @RequestHeader String password){
        return userService.setPassword(token, password);
    }

    @GetMapping("getUserByName")
    public ResponseEntity<ApiResponse> getUserByName(@RequestHeader String name){
        return userService.getUserByName(name);
    }

    @GetMapping("findUserByTeam")
    public ResponseEntity<ApiResponse> findUserByTeam(@RequestHeader String team, @RequestHeader(defaultValue = "0") int page, @RequestHeader(defaultValue = "10") int size){
        return userService.findUserByTeam(team, page, size);
    }

    @GetMapping("getAllActiveUsers")
    public ResponseEntity<ApiResponse> getAllActiveUsers(@RequestHeader(defaultValue = "0") int page, @RequestHeader(defaultValue = "10") int size){
        return userService.getAllActiveUsers(page, size);
    }

    @DeleteMapping("deleteUserByEmail")
    public ResponseEntity<ApiResponse> deleteUserByEmail(@RequestHeader String email){
        return userService.deleteUserByEmail(email);
    }

    @GetMapping("getUsersAssignedTo")
    public ResponseEntity<ApiResponse> getUsersAssignedTo(@RequestHeader(defaultValue = "0") int page, @RequestHeader(defaultValue = "10") int size){
        return userService.getUsersAssignedTo(page, size);
    }

    @GetMapping("searchUsersToAssign")
    public ResponseEntity<ApiResponse> searchUsersToAssign(@RequestHeader String searchQuery){
        return userService.searchUsersToAssign(searchQuery);
    }

    @GetMapping("getUsersCreatedBy")
    public ResponseEntity<ApiResponse> getUsersCreatedBy(@RequestHeader(required = false) String name, @RequestHeader(required = false) String email,@RequestHeader(defaultValue = "0") int page, @RequestHeader(defaultValue = "10") int size) {
        return userService.getUsersCreatedBy(name, email, page , size);
    }

    @GetMapping("findUserByEmailAndPassword")
    public ResponseEntity<ApiResponse> findUserByEmailAndPassword(@RequestHeader String email, @RequestHeader String password){
        return userService.findByEmailAndPassword(email, password);
    }

    @PatchMapping("updateAccess")
    public ResponseEntity<ApiResponse> updateAccess(@RequestHeader String email, @RequestHeader String userEmail){
        return userService.updateAccess(email, userEmail);
    }

    @GetMapping("findActiveUsersByTeam")
    public ResponseEntity<ApiResponse> findActiveUsersByTeam(@RequestParam String team, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)  {
        return userService.findActiveUsersByTeam(team, page, size);
    }
}