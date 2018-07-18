package com.opsgenie.onboarding.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opsgenie.onboarding.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class UserDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonIgnore
    public static UserDTO from(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setName(user.getName());
    }

    @JsonIgnore
    public User toUser() {
        return new User()
                .setId(getId())
                .setName(getName());
    }

    public String getId() {
        return id;
    }

    public UserDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(name, userDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
