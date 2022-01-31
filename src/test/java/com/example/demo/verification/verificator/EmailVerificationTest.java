package com.example.demo.verification.verificator;

import com.example.demo.logger.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailVerificationTest {

    @Test
    public void EmailVerificationTest() {
        EmailVerificationStrategy emailVerificationStrategy = new EmailVerificationStrategy();
        emailVerificationStrategy.verify("");
        assertTrue(Logger.contains("email"));
    }
}
