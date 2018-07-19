package com.opsgenie.onboarding.team.support;

import com.opsgenie.aws.dynamodb.AmazonDynamoDbAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AmazonTeamRepositoryExtTest {

    private TeamRepository repository;

    @Before
    public void setUp() {
        repository = new AmazonTeamRepository(new AmazonDynamoDbAccessor());
    }

    @After
    public void tearDown() {
        repository = null;
    }

    @Test
    public void todo() throws Exception {

    }
}
