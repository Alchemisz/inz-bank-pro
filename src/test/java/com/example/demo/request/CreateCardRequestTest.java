package com.example.demo.request;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.card.CardService;
import com.example.demo.card.builder.CardDirector;
import com.example.demo.card.builder.directorFactory.CardDirectorFactory;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCardRequestTest {
    @Autowired
    CardDirectorFactory cardDirectorFactory;

    @Autowired
    CardService cardService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    RequestFactory requestFactory;

    @Test
    public void validTransferRequestTest() {
        BankAccount account = bankAccountService.getBankAccount("26922018960603293159613803");
        int listSize = account.getCardList().size();
        RequestOrder requestOrder = requestFactory.createCreateCardRequest(cardService, account.getAccountNumber(), cardDirectorFactory.getCardDirector());
        requestOrder.execute();

        account = bankAccountService.getBankAccount("26922018960603293159613803");
        assertEquals(account.getCardList().size(), listSize+1);
    }
}
