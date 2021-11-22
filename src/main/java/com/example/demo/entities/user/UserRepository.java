package com.example.demo.entities.user;

import java.util.Optional;

public interface UserRepository {

    User getUser(String login);
    void insertUser(User user);
    void deleteUser(String login);
}
