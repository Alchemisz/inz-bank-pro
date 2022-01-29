package com.example.demo.verification.verificator;

import com.example.demo.request.RequestOrder;
import com.example.demo.verification.VerificationObject;
import com.example.demo.verification.VerificationService;

public class BlockBankAccountVerificator extends AbstractVerificator{

    public BlockBankAccountVerificator(VerificationStrategy verificationStrategy, VerificationService verificationService) {
        super(verificationStrategy, verificationService);
    }

    @Override
    public String startVerification(RequestOrder requestOrder) {
        VerificationObject verificationObject = verificationService.registerRequest(requestOrder);
        String message = "you are trying to block account validation code: " + verificationObject.getCode();
        verificationStrategy.verify(message);
        return verificationObject.getCode();
    }

}
