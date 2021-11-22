package com.example.demo.entities.security;

import com.example.demo.entities.request.RequestOrder;
import com.example.demo.entities.user.User;

import javax.servlet.http.HttpSession;

public class LoginRequest implements RequestOrder {
    private final HttpSession httpSession;
    private final User user;

    public LoginRequest(HttpSession httpSession, User user) {
        this.httpSession = httpSession;
        this.user = user;
    }

    @Override
    public void execute() {
        httpSession.setAttribute("user", user);
    }
}
