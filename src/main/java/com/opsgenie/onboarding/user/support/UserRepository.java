package com.opsgenie.onboarding.user.support;

import com.opsgenie.onboarding.user.User;

import java.util.List;

public interface UserRepository {

    List<User> listUsers();

    User getUser(String userId);

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(User user);
}
