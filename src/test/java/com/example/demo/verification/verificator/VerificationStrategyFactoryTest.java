package com.example.demo.verification.verificator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VerificationStrategyFactoryTest {
    @Autowired
    private VerificationStrategyFactory verificationStrategyFactory;
    @Test
    public void createEmailVerificationStrategyTest() {
        VerificationStrategy verificationStrategy = verificationStrategyFactory.getDesiredStrategy(VerificationType.EMAIL);
        assertEquals(verificationStrategy.getClass(), EmailVerificationStrategy.class);
    }
}
