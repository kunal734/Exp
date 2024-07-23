package com.tekpyramid.supportportal.dao.impl;

import com.tekpyramid.supportportal.dao.TeamDao;
import com.tekpyramid.supportportal.data.models.entity.Team;
import com.tekpyramid.supportportal.repository.TeamRepository;
import com.tekpyramid.supportportal.utils.TeamNameProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeamDaoImpl implements TeamDao {

    private final TeamRepository teamRepository;

    @Override
    public Team saveTeam(Team team) {
        return Optional.of(teamRepository.save(team)).orElseThrow(() -> new NoSuchElementException("Error saving team"));
    }

    @Override
    public Optional<List<String>> getTeamNames() {
        return Optional.of(teamRepository.findAllTeamNames().stream()
                .map(TeamNameProjection::getName)
                .toList());

    }

    @Override
    public Optional<Team> findByName(String name) {
        return Optional.ofNullable(teamRepository.findByName(name)).orElseThrow(() -> new NoSuchElementException("Error finding team by name"));
    }

    @Override
    public Page<Team> getAllTeams(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return Optional.of(teamRepository.findAll(pageable)).orElseThrow(() -> new NoSuchElementException("Error getting teams"));
    }

    @Override
    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }

    @Override
    public long countTeam() {
        return teamRepository.count();
    }

}
