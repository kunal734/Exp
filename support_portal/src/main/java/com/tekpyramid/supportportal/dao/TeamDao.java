package com.tekpyramid.supportportal.dao;

import com.tekpyramid.supportportal.data.models.entity.Team;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TeamDao {

    Team saveTeam(Team team);

    Optional<List<String>> getTeamNames();

    Optional<Team> findByName(String name);

    Page<Team> getAllTeams(int page, int size);

    void deleteTeam(Team team);

    long countTeam();
}
