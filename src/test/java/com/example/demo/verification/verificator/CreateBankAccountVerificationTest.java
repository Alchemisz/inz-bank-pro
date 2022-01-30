package com.example.demo.verification.verificator;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.request.CreateBankAccountRequest;
import com.example.demo.request.CreateBankAccountRequestTest;
import com.example.demo.security.priviledges.UserPriviledges;
import com.example.demo.user.User;
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
public class CreateBankAccountVerificationTest {
    @Autowired
    private VerificationService verificationService;

    @Autowired
    private BankAccountService bankAccountService;

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
    public void createBankAccountVerificatorTest() {
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

        AbstractVerificator verificator = verificatorAbstractFactory.getCreateBankAccountVerificator(VerificationType.EMAIL);
        assertEquals(verificator.getClass(), CreateBankAccountVerificator.class);
        CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest(new BankAccount(), new User("", "", UserPriviledges.getClientPriviledges()), bankAccountService);
        String id = verificator.startVerification(createBankAccountRequest);

        assertFalse(logger.isEmpty());
        assertTrue(logger.get(0).contains("account") && logger.get(0).contains("code"));
        logger.clear();
    }
}
