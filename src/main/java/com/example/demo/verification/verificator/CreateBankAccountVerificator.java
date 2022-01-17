package com.example.demo.verification.verificator;

import com.example.demo.request.RequestOrder;
import com.example.demo.verification.VerificationObject;
import com.example.demo.verification.VerificationService;

public class CreateBankAccountVerificator extends AbstractVerificator {

    public CreateBankAccountVerificator(VerificationStrategy verificationStrategy, VerificationService verificationService) {
        super(verificationStrategy, verificationService);
    }

    @Override
    public String startVerification(RequestOrder requestOrder) {
        VerificationObject verificationObject = verificationService.registerRequest(requestOrder);
        System.out.println("Trying to create new bank account....");
        return verificationObject.getCode();
    }
}
