package com.example.demo.request;

import com.example.demo.user.User;

import javax.servlet.http.HttpSession;

public class LoginRequest implements RequestOrder {
    private final HttpSession httpSession;
    private final User user;

    public LoginRequest(HttpSession httpSession, User user) {
        this.httpSession = httpSession;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void execute() {
        httpSession.setAttribute("user", user);
    }
}
