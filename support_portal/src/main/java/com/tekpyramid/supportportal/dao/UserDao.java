package com.tekpyramid.supportportal.dao;

import com.tekpyramid.supportportal.data.models.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User saveUser(User user);

    Page<User> getAllUsers(int page, int size);

    Optional<User> findUserByEmail(String email);

    Optional<List<User>> findUserByName(String name);

    Optional<Page<User>> findUserByTeam(String team, int page, int size);

    Optional<User> findByToken(String token);

    Optional<Page<User>> getAllActiveUsers(int page, int size);

    void deleteUserByEmail(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<Page<User>> getUsersCreatedByNameOrEmail(String name, String email, int page, int size);

    Optional<Page<User>> getUsersAssignedTo(int page, int size);

    Optional<List<User>> searchUsersToAssign(String searchQuery);

    Optional<Page<User>> findActiveUsersByTeam(String team, int page, int size);

    long countUser();

}
