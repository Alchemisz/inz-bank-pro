package com.example.demo.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.Test;

import javax.annotation.PostConstruct;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HashingServiceTest {

    private  HashingService hashingService;

    public HashingServiceTest() {
        this.hashingService = new HashingService();
    }

    @Test
    public void rightPasswordTest(){
        String hash = BCrypt.withDefaults().hashToString(hashingService.cost, "test".toCharArray());
        String pass = "test";
        assertTrue(hashingService.verify(hash, pass));
    }

    @Test
    public void wrongPasswordTest(){
        String hash = BCrypt.withDefaults().hashToString(hashingService.cost, "test".toCharArray());
        String pass = "test2";
        assertFalse(hashingService.verify(hash, pass));
    }

}
