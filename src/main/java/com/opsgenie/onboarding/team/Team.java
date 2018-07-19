package com.opsgenie.onboarding.team;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "onboardingTeam")
public class Team {

    private String id;
    private String name;
    private Set<String> userIds;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public Team setId(String id) {
        this.id = id;
        return this;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    @DynamoDBAttribute(attributeName = "userIds")
    public Set<String> getUserIds() {
        return userIds;
    }

    public Team setUserIds(Set<String> userIds) {
        this.userIds = userIds;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(userIds, team.userIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userIds);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("userIds", userIds)
                .toString();
    }
}
