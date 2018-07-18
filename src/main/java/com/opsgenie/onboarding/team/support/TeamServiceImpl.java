package com.opsgenie.onboarding.team.support;

import com.opsgenie.onboarding.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;

    @Autowired
    public TeamServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Team> listTeams() {
        return repository.listTeams();
    }

    @Override
    public Team getTeam(String teamId) {
        return repository.getTeam(teamId);
    }

    @Override
    public Team addTeam(Team team) {
        team.setId(UUID.randomUUID().toString());
        return repository.addTeam(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return repository.updateTeam(team);
    }

    @Override
    public void deleteTeam(Team team) {
        repository.deleteTeam(team);
    }
}
