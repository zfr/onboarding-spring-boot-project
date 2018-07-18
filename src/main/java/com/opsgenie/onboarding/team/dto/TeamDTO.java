package com.opsgenie.onboarding.team.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opsgenie.onboarding.team.Team;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.Set;

public class TeamDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("userIds")
    private Set<String> userIds;

    @JsonIgnore
    public static TeamDTO from(Team team) {
        return new TeamDTO()
                .setId(team.getId())
                .setName(team.getName())
                .setUserIds(team.getUserIds());
    }

    @JsonIgnore
    public Team toTeam() {
        return new Team()
                .setId(getId())
                .setName(getName())
                .setUserIds(getUserIds());
    }

    public String getId() {
        return id;
    }

    public TeamDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeamDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Set<String> getUserIds() {
        return userIds;
    }

    public TeamDTO setUserIds(Set<String> userIds) {
        this.userIds = userIds;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDTO teamDTO = (TeamDTO) o;
        return Objects.equals(id, teamDTO.id) &&
                Objects.equals(name, teamDTO.name) &&
                Objects.equals(userIds, teamDTO.userIds);
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
