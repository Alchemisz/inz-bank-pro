package com.example.demo.controllers.rest;

import com.example.demo.entities.user.UserRepository;
import com.example.demo.security.HashingService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/auth")
public class AuthController {

    UserRepository userRepository;

    HashingService hashingService;

    HttpSession httpSession;

    public AuthController(UserRepository userRepository, HashingService hashingService, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public Boolean login(HttpServletRequest httpRequest) {
        String auth = httpRequest.getHeader("Authentication");
        if(auth != null) {
            String[] parts = auth.split(":");
            if(parts.length == 2) {
                userRepository.getUser(parts[0]).ifPresentOrElse(user -> {
                    String pass = parts[1];
                    String userHash = user.getPass();
                    if(hashingService.verify(userHash, pass)) {
                        httpSession.setAttribute("user", user);
                    } else {
                        httpSession.setAttribute("user", null);
                    }
                }, () -> {
                    httpSession.setAttribute("user", null);
                });
            } else {
                httpSession.setAttribute("user", null);
            }
        } else {
            httpSession.setAttribute("user", null);
        }
        return httpSession.getAttribute("user") != null;
    }
}
