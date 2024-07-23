package com.tekpyramid.supportportal.repository;

import com.tekpyramid.supportportal.data.models.entity.Team;
import com.tekpyramid.supportportal.utils.TeamNameProjection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team,String> {

    @Query(value = "{}", fields = "{ 'name' : 1 }")
    List<TeamNameProjection> findAllTeamNames();

    Optional<Team> findByName(String name);
}
