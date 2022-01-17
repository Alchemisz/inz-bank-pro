package com.example.demo.verification;


public interface VerificationRepository {
    public VerificationObject getVerificationObject(String id);
    public void removeVerificationObject(String id);
    public void registerVerificationObject(String id, VerificationObject verificationObject);
}
