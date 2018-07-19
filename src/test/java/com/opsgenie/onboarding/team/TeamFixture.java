package com.opsgenie.onboarding.team;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

public class TeamFixture {

    public static Team unique() {
        Set<String> userIds = new HashSet<>();
        final int numOfUsers = RandomUtils.nextInt(0, 4);
        IntStream.of(numOfUsers)
                .forEach(value -> userIds.add(UUID.randomUUID().toString()));

        return new Team()
                .setId(UUID.randomUUID().toString())
                .setName(RandomStringUtils.random(20))
                .setUserIds(userIds);
    }
}
