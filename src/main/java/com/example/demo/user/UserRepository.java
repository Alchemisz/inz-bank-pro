package com.example.demo.user;

import java.util.List;

public interface UserRepository {

    User getUser(String login);
    void insertUser(User user);
    void deleteUser(String login);

    List<User> getAll();
}
