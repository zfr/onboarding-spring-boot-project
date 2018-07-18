package com.opsgenie.onboarding.team.support;

import com.opsgenie.onboarding.team.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTeamRepository implements TeamRepository {

    private final Map<String, Team> accessor;

    public InMemoryTeamRepository() {
        accessor = new LinkedHashMap<>();
    }

    @Override
    public List<Team> listTeams() {
        return new ArrayList<>(accessor.values());
    }

    @Override
    public Team getTeam(String teamId) {
        return accessor.get(teamId);
    }

    @Override
    public Team addTeam(Team team) {
        accessor.put(team.getId(), team);
        return team;
    }

    @Override
    public Team updateTeam(Team team) {
        accessor.put(team.getId(), team);
        return team;
    }

    @Override
    public void deleteTeam(Team team) {
        accessor.remove(team.getId());
    }
}
