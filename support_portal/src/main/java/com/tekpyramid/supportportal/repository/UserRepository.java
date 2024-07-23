package com.tekpyramid.supportportal.repository;

import com.tekpyramid.supportportal.data.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<List<User>> findByName(String name);

    @Query("{'email': ?0}, {'password': ?1}")
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByToken(String token);

    Optional<Page<User>> findUserByCreatedByNameOrCreatedByEmail(String name, String email, Pageable pageable);

    @Query("{'team': ?0}")
    Optional<Page<User>> findByTeam(String team, Pageable pageable);

    @Query("{'status': 'ACTIVE'}")
    Optional<Page<User>> getAllActiveUsers(Pageable pageable);

    void deleteByEmail(String email);
}
