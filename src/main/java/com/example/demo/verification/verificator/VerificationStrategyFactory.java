package com.example.demo.verification.verificator;

import org.springframework.stereotype.Component;

interface VerificationStrategyFactory {
    public VerificationStrategy getDesiredStrategy(VerificationType type);
}
