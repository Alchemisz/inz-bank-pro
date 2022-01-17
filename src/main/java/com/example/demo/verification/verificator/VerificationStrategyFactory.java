package com.example.demo.verification.verificator;

import org.springframework.stereotype.Component;

@Component
class VerificationStrategyFactory {

    public VerificationStrategy getDesiredStrategy(VerificationType type) {
        switch(type) {
            case EMAIL:
                return new EmailVerificationStrategy();
            default:
                return null;
        }
    }
}
