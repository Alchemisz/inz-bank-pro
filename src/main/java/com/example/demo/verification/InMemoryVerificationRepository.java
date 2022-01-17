package com.example.demo.verification;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryVerificationRepository implements VerificationRepository{
    private ConcurrentHashMap<String, VerificationObject> orderMap;

    public InMemoryVerificationRepository() {
        this.orderMap = new ConcurrentHashMap<>();
    }

    @Override
    public VerificationObject getVerificationObject(String id) {
        return orderMap.get(id);
    }

    @Override
    public void removeVerificationObject(String id) {
        orderMap.remove(id);
    }

    @Override
    public void registerVerificationObject(String id, VerificationObject verificationObject) {
        orderMap.put(id, verificationObject);
    }
}
