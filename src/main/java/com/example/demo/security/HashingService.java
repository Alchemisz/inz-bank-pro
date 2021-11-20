package com.example.demo.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HashingService {
    int cost;

    public HashingService() {
        this.cost = 5;
    }

    private String hash(String pass) {
        return BCrypt.withDefaults().hashToString(cost, pass.toCharArray());
    }

    public boolean verify(String hash, String pass) {
        return BCrypt.verifyer().verify(pass.getBytes(), hash.getBytes()).verified;
    }
}
