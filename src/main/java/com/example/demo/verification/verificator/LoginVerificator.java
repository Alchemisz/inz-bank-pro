package com.example.demo.verification.verificator;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.RequestOrder;
import com.example.demo.user.User;
import com.example.demo.verification.VerificationObject;
import com.example.demo.verification.VerificationService;

class LoginVerificator extends AbstractVerificator {
    public LoginVerificator(VerificationStrategy verificationStrategy, VerificationService verificationService) {
        super(verificationStrategy, verificationService);
    }

    @Override
    public String startVerification(RequestOrder requestOrder) {
        LoginRequest loginRequest = (LoginRequest) requestOrder;
        User user = loginRequest.getUser();
        VerificationObject verificationObject = verificationService.registerRequest(requestOrder);
        String message = "you are trying to login on " + user.getLogin() + " account. validation code: " + verificationObject.getCode();
        return verificationObject.getCode();
    }
}
