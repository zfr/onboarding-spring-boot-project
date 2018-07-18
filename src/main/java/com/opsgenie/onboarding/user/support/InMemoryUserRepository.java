package com.opsgenie.onboarding.user.support;

import com.opsgenie.onboarding.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> accessor;

    public InMemoryUserRepository() {
        accessor = new LinkedHashMap<>();
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(accessor.values());
    }

    @Override
    public User getUser(String userId) {
        return accessor.get(userId);
    }

    @Override
    public User addUser(User user) {
        accessor.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        accessor.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        accessor.remove(user.getId());
    }
}
