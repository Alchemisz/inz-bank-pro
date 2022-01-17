package com.example.demo.controllers.rest;

import com.example.demo.security.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;

    HttpSession httpSession;

    public AuthController(AuthService authService, HttpSession httpSession) {
        this.authService = authService;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public Map<String, String> login(HttpServletRequest httpRequest) {
        Map<String, String> response = new HashMap<>();
        String auth = httpRequest.getHeader("Authentication");
        if(auth != null) {
            String[] parts = auth.split(":");
            if(parts.length == 2) {
                String id = authService.requestAuth(parts[0], parts[1]);
                response.put("requestId", id);
            } else {
                response.put("errorMsg", "use login:pass format");
            }
        } else {
            response.put("errorMsg", "lacking authentication header");
        }
        return response;
    }
}
