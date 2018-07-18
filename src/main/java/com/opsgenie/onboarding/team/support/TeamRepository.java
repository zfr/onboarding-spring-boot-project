package com.opsgenie.onboarding.team.support;

import com.opsgenie.onboarding.team.Team;

import java.util.List;

public interface TeamRepository {

    List<Team> listTeams();

    Team getTeam(String teamId);

    Team addTeam(Team team);

    Team updateTeam(Team team);

    void deleteTeam(Team team);
}
