package com.example.demo.verification;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryVerificationRepository implements VerificationRepository{
    private ConcurrentHashMap<String, VerificationObject> orderMap;
    private Long id;

    public InMemoryVerificationRepository() {
        this.orderMap = new ConcurrentHashMap<>();
        this.id = 0L;
    }
    private String getId() {
        String id = this.id.toString();
        this.id += 1;
        return id;
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
    public String registerVerificationObject(VerificationObject verificationObject) {
        String id = getId();
        orderMap.put(id, verificationObject);
        return id;
    }
}
