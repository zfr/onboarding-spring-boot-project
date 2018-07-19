package com.opsgenie.onboarding.team;

import com.opsgenie.onboarding.exception.EntityNotFoundException;
import com.opsgenie.onboarding.team.dto.TeamDTO;
import com.opsgenie.onboarding.team.support.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
public class TeamRestController {

    private final TeamService teamService;

    @Autowired
    public TeamRestController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TeamDTO> listTeams() {
        return teamService.listTeams().stream()
                .map(TeamDTO::from)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public TeamDTO addTeam(@RequestBody TeamDTO teamDTO) {
        final Team team = teamDTO.toTeam();

        final Team addedTeam = teamService.addTeam(team);

        return TeamDTO.from(addedTeam);
    }

    @RequestMapping(value = "/{teamId}", method = RequestMethod.PUT)
    public TeamDTO updateTeam(@PathVariable String teamId, @RequestBody TeamDTO teamDTO) {
        final Team team = teamService.getTeam(teamId);

        if (team == null) {
            throw new EntityNotFoundException("Team not found with identifier [" + teamId + "]");
        }

        team.setName(teamDTO.getName());
        team.setUserIds(teamDTO.getUserIds());

        final Team updatedTeam = teamService.updateTeam(team);

        return TeamDTO.from(updatedTeam);
    }

    @RequestMapping(value = "/{teamId}", method = RequestMethod.GET)
    public TeamDTO getTeam(@PathVariable String teamId) {
        final Team team = teamService.getTeam(teamId);

        if (team == null) {
            throw new EntityNotFoundException("Team not found with identifier [" + teamId + "]");
        }

        return TeamDTO.from(team);
    }

    @RequestMapping(value = "/{teamId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTeam(@PathVariable String teamId) {
        final Team team = teamService.getTeam(teamId);

        if (team == null) {
            throw new EntityNotFoundException("Team not found with identifier [" + teamId + "]");
        }

        teamService.deleteTeam(team);

        return ResponseEntity.ok().build();
    }
}
