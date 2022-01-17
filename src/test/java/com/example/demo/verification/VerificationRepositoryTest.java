package com.example.demo.verification;

import com.example.demo.request.RequestOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VerificationRepositoryTest {

    @Autowired
    private InMemoryVerificationRepository verificationRepository;

    @Test
    public void insertAndGetTest() {
        VerificationObject verificationObject = new VerificationObject("test", new RequestOrder() {
            @Override
            public void execute() {

            }
        });

        verificationRepository.registerVerificationObject("test", verificationObject);
        VerificationObject obj = verificationRepository.getVerificationObject("test");
        assertEquals(obj, verificationObject);
    }
    @Test
    public void removeTest() {
        verificationRepository.removeVerificationObject("test");
        assertNull(verificationRepository.getVerificationObject("test"));
    }
}
