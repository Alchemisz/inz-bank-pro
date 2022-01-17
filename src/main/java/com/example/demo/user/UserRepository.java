package com.example.demo.user;

public interface UserRepository {

    User getUser(String login);
    void insertUser(User user);
    void deleteUser(String login);
}
