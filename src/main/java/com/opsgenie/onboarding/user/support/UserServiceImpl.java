package com.opsgenie.onboarding.user.support;

import com.opsgenie.onboarding.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(@Qualifier(value = "amazonUserRepository") UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> listUsers() {
        return repository.listUsers();
    }

    @Override
    public User getUser(String userId) {
        return repository.getUser(userId);
    }

    @Override
    public User addUser(User user) {
        return repository.addUser(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        repository.deleteUser(user);
    }
}
