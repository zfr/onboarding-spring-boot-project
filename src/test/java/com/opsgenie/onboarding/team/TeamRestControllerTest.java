package com.opsgenie.onboarding.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.opsgenie.onboarding.team.support.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamRestController.class)
public class TeamRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    public void listTeams_emptyTeamsCollection() throws Exception {
        assertListTeams(Collections.emptyList());
    }

    @Test
    public void listTeams_nonEmptyTeamsCollection() throws Exception {
        List<Team> expectedTeams = Arrays.asList(
                new Team()
                        .setId("id1")
                        .setName("name1")
                        .setUserIds(Stream.of("user1", "user2").collect(Collectors.toSet())),
                new Team()
                        .setId("id2")
                        .setName("name2")
                        .setUserIds(Stream.of("user2", "user3").collect(Collectors.toSet()))
        );
        assertListTeams(expectedTeams);
    }

    private void assertListTeams(List<Team> expectedTeams) throws Exception {
        when(teamService.listTeams()).thenReturn(expectedTeams);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedJsonOutput = objectWriter.writeValueAsString(expectedTeams);

        mockMvc.perform(get("/teams"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput));

        verify(teamService, times(1)).listTeams();
    }
}
