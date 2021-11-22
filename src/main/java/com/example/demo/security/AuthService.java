package com.example.demo.security;

import com.example.demo.entities.security.LoginRequest;
import com.example.demo.entities.user.User;
import com.example.demo.entities.user.UserRepository;
import com.example.demo.verification.VerificationService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private VerificationService verificationService;

    private HashingService hashingService;

    private HttpSession httpSession;

    public AuthService(UserRepository userRepository, VerificationService verificationService, HashingService hashingService, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.verificationService = verificationService;
        this.hashingService = hashingService;
        this.httpSession = httpSession;
    }

    public String requestAuth(String login, String pass) {
        httpSession.setAttribute("user", null);
        User user = userRepository.getUser(login);
        if(user != null) {
            String userHash = user.getPass();
            if(hashingService.verify(userHash, pass)) {
                LoginRequest loginRequest = new LoginRequest(httpSession, user);
                String id = verificationService.registerLoginRequest(loginRequest);
                return id;
            }
        }
        return null;
    }
}
