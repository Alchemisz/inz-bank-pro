package com.example.demo.verification.verificator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VerificatorAbstractFactoryTest {
    @Autowired
    private VerificatorAbstractFactory verificatorAbstractFactory;

    @Test
    public void getLoginVerificatorTest() {
        AbstractVerificator verificator = verificatorAbstractFactory.getLoginVerificator(VerificationType.EMAIL);
        assertEquals(verificator.getClass(), LoginVerificator.class);
    }

    @Test
    public void getTransferVerificatorTest() {
        AbstractVerificator verificator = verificatorAbstractFactory.getTransferVerificator(VerificationType.EMAIL);
        assertEquals(verificator.getClass(), TransferVerificator.class);
    }

    @Test
    public void getCreateBankAccountVerificatorTest() {
        AbstractVerificator verificator = verificatorAbstractFactory.getCreateBankAccountVerificator(VerificationType.EMAIL);
        assertEquals(verificator.getClass(), CreateBankAccountVerificator.class);
    }
}
