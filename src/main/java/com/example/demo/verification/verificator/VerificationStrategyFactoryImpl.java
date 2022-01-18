package com.example.demo.verification.verificator;

import org.springframework.stereotype.Component;

@Component
public class VerificationStrategyFactoryImpl implements VerificationStrategyFactory {

    public VerificationStrategy getDesiredStrategy(VerificationType type) {
        switch(type) {
            case EMAIL:
                return new EmailVerificationStrategy();
            default:
                return null;
        }
    }
}
