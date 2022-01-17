package com.example.demo.verification.verificator;

import com.example.demo.verification.VerificationService;
import org.springframework.stereotype.Component;

@Component
public class VerificatorAbstractFactory {
    private VerificationStrategyFactory verificationStrategyFactory;
    private VerificationService verificationService;

    public VerificatorAbstractFactory(VerificationStrategyFactory verificationStrategyFactory, VerificationService verificationService) {
        this.verificationStrategyFactory = verificationStrategyFactory;
        this.verificationService = verificationService;
    }

    public AbstractVerificator getLoginVerificator(VerificationType type) {
        return new LoginVerificator(verificationStrategyFactory.getDesiredStrategy(type), verificationService);
    }
    public AbstractVerificator getTransferVerificator(VerificationType type) {
        return new TransferVerificator(verificationStrategyFactory.getDesiredStrategy(type), verificationService);
    }
    public AbstractVerificator getCreateBankAccountVerificator(VerificationType type) {
        return new CreateBankAccountVerificator(verificationStrategyFactory.getDesiredStrategy(type), verificationService);
    }

}
