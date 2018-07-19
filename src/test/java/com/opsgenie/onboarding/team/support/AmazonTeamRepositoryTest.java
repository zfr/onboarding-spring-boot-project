package com.opsgenie.onboarding.team.support;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.opsgenie.aws.dynamodb.AmazonDynamoDbAccessor;
import com.opsgenie.onboarding.team.Team;
import com.opsgenie.onboarding.team.TeamFixture;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AmazonTeamRepositoryTest {

    @Mock
    private AmazonDynamoDbAccessor accessor;

    private AmazonTeamRepository repository;

    @Before
    public void setUp() {
        repository = new AmazonTeamRepository(accessor);
    }

    @After
    public void tearDown() {
        repository = null;
    }

    @Test
    public void listTeams() {
        List<Team> expectedTeams = Arrays.asList(
                TeamFixture.unique(),
                TeamFixture.unique()
        );
        when(accessor.scan(eq(Team.class), any(DynamoDBScanExpression.class))).thenReturn(expectedTeams);

        final List<Team> teams = repository.listTeams();

        assertThat(teams, is(equalTo(expectedTeams)));
        verify(accessor, times(1)).scan(eq(Team.class), any(DynamoDBScanExpression.class));
    }

    @Test
    public void getTeam() {
        Team expectedTeam = TeamFixture.unique();
        String teamId = expectedTeam.getId();
        when(accessor.load(Team.class, teamId)).thenReturn(expectedTeam);

        final Team team = repository.getTeam(teamId);

        assertThat(team, is(equalTo(expectedTeam)));
        verify(accessor, times(1)).load(Team.class, teamId);
    }
}
