package com.example.demo.verification.verificator;

import com.example.demo.request.TransferRequest;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferService;
import com.example.demo.verification.VerificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferVerificationTest {
    @Autowired
    private VerificationService verificationService;

    @Autowired
    private TransferService transferService;

    private volatile List<String> logger = new ArrayList<>();

    private VerificationStrategyFactory factoryMock;
    private VerificatorAbstractFactory verificatorAbstractFactory;

    @Before
    public void init() {
        this.factoryMock = new VerificationStrategyFactory() {

            @Override
            public VerificationStrategy getDesiredStrategy(VerificationType type) {
                return new VerificationStrategy() {
                    @Override
                    public void verify(String message) {
                        logger.add(message);
                    }
                };
            }
        };
        this.verificatorAbstractFactory = new VerificatorAbstractFactory(factoryMock, verificationService);
    }

    @Test
    public void TransferVerificatorTest() {
        VerificationStrategyFactory factoryMock = new VerificationStrategyFactory() {

            @Override
            public VerificationStrategy getDesiredStrategy(VerificationType type) {
                return new VerificationStrategy() {
                    @Override
                    public void verify(String message) {
                        logger.add(message);
                    }
                };
            }
        };
        VerificatorAbstractFactory verificatorAbstractFactory = new VerificatorAbstractFactory(factoryMock, verificationService);

        AbstractVerificator verificator = verificatorAbstractFactory.getTransferVerificator(VerificationType.EMAIL);
        assertEquals(verificator.getClass(), TransferVerificator.class);

        TransferRequest transferRequest = new TransferRequest(new Transfer("0", "0", "0", new BigDecimal(20)), transferService);
        String id = verificator.startVerification(transferRequest);

        assertFalse(logger.isEmpty());
        assertTrue(logger.get(0).contains("send") && logger.get(0).contains("code"));
        logger.clear();
    }
}
