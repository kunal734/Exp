package com.tekpyramid.supportportal.dao.impl;

import com.tekpyramid.supportportal.dao.UserDao;
import com.tekpyramid.supportportal.data.models.entity.User;
import com.tekpyramid.supportportal.data.models.enums.Status;
import com.tekpyramid.supportportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public User saveUser(User user) {
        return Optional.of(userRepository.save(user)).orElseThrow(() -> new NoSuchElementException("User not created"));
    }

    @Override
    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<List<User>> findUserByName(String name) {
        return Optional.ofNullable(userRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("User not found")));
    }

    @Override
    public Optional<Page<User>> findUserByTeam(String team, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByTeam(team, pageable);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional.ofNullable(userRepository.findByToken(token).orElseThrow(() -> new NoSuchElementException("Unauthorised User")));
    }

    @Override
    public Optional<Page<User>> getAllActiveUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.getAllActiveUsers(pageable);
    }

    @Override
    public void deleteUserByEmail(User user) {
        userRepository.delete(user);

    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return Optional.ofNullable(userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new NoSuchElementException("User not found")));
    }

    @Override
    public Optional<Page<User>> getUsersCreatedByNameOrEmail(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return Optional.ofNullable(userRepository.findUserByCreatedByNameOrCreatedByEmail(name, email, pageable).orElseThrow(() -> new NoSuchElementException("User not found")));
    }

    @Override
    public Optional<Page<User>> getUsersAssignedTo(int page , int size) {
        Pageable pageable = PageRequest.of(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("numberOfTicket").gt(0));
        return Optional.of(new PageImpl<>(mongoTemplate.find(query.with(pageable), User.class)));
    }

    @Override
    public Optional<List<User>> searchUsersToAssign(String searchQuery) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(Status.ACTIVE)
                .orOperator(new Criteria().orOperator( Criteria.where("name").regex(searchQuery,"i")
                        , Criteria.where("team").regex(searchQuery, "i"))));
        return Optional.of(mongoTemplate.find(query, User.class));
    }

    @Override
    public Optional<Page<User>> findActiveUsersByTeam(String team, int page, int size) {
        Query query = new Query();
        query.addCriteria(Criteria.where("team").is(team).andOperator(Criteria.where("status").is("ACTIVE")));
        return Optional.of(new PageImpl<>(mongoTemplate.find(query.with(PageRequest.of(page, size)), User.class)));
    }

    @Override
    public long countUser() {
        return userRepository.count();
    }
}
