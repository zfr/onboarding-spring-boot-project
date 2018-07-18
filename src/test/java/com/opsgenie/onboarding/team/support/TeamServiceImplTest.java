package com.opsgenie.onboarding.team.support;

import com.opsgenie.onboarding.team.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceImplTest {

    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Before
    public void setUp() {
        teamService = new TeamServiceImpl(teamRepository);
    }

    @After
    public void tearDown() {
        teamRepository = null;
        teamService = null;
    }

    @Test
    public void listTeams() throws Exception {
        final List<Team> expectedTeams = Arrays.asList(
                new Team().setId("id1"),
                new Team().setId("id2")
        );

        when(teamRepository.listTeams()).thenReturn(expectedTeams);

        final List<Team> teams = teamService.listTeams();

        assertThat(teams, is(equalTo(expectedTeams)));

        verify(teamRepository, times(1)).listTeams();
    }
}
