package com.example.demo.verification.verificator;

import com.example.demo.logger.Logger;

class EmailVerificationStrategy implements VerificationStrategy{

    @Override
    public void verify(String message) {
        Logger.log("email verification -> " + message);
    }
}
