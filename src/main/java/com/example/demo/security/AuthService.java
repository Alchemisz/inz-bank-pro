package com.example.demo.security;

import com.example.demo.request.LoginRequest;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.verification.VerificationService;
import com.example.demo.verification.verificator.AbstractVerificator;
import com.example.demo.verification.verificator.VerificationType;
import com.example.demo.verification.verificator.VerificatorAbstractFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {

    private UserRepository userRepository;

    private VerificatorAbstractFactory verificatorAbstractFactory;

    private HashingService hashingService;

    private HttpSession httpSession;

    public AuthService(UserRepository userRepository, VerificatorAbstractFactory verificatorAbstractFactory, HashingService hashingService, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.verificatorAbstractFactory = verificatorAbstractFactory;
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
                AbstractVerificator loginVerificator = verificatorAbstractFactory.getLoginVerificator(VerificationType.EMAIL);
                String id = loginVerificator.startVerification(loginRequest);
                return id;
            }
        }
        return null;
    }
}
