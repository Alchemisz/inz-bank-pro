package com.example.demo.verification.verificator;

import com.example.demo.card.CardService;
import com.example.demo.card.builder.directorFactory.CardDirectorFactory;
import com.example.demo.request.RequestFactory;
import com.example.demo.request.RequestOrder;
import com.example.demo.verification.VerificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivateCardVerificatorTest {
    @Autowired
    private VerificationService verificationService;

    @Autowired
    private CardService cardService;

    @Autowired
    private RequestFactory requestFactory;

    @Autowired
    private CardDirectorFactory cardDirectorFactory;

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
    public void createCardVerificatorTest() {
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

        AbstractVerificator verificator = verificatorAbstractFactory.getActivateCardVerificator(VerificationType.EMAIL);
        assertEquals(verificator.getClass(), ActivateCardVerificator.class);

        RequestOrder activateCardTransfer = requestFactory.createBlockCardRequest(cardService, "0");
        String id = verificator.startVerification(activateCardTransfer);

        assertFalse(logger.isEmpty());
        assertTrue(logger.get(0).contains("activate") && logger.get(0).contains("code"));
        logger.clear();
    }
}
