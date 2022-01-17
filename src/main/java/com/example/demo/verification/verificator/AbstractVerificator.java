package com.example.demo.verification.verificator;

import com.example.demo.request.RequestOrder;
import com.example.demo.verification.VerificationService;

public abstract class AbstractVerificator {
    VerificationStrategy verificationStrategy;
    VerificationService verificationService;

    public AbstractVerificator(VerificationStrategy verificationStrategy, VerificationService verificationService) {
        this.verificationStrategy = verificationStrategy;
        this.verificationService = verificationService;
    }

    public abstract String startVerification(RequestOrder requestOrder);
}
