package com.example.demo.Card;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.card.Card;
import com.example.demo.card.CardRepositoryMemoryImpl;
import com.example.demo.card.CardServiceImpl;
import com.example.demo.card.SqlCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardServiceTest {

    @Autowired
    private SqlCardRepository sqlCardRepository;
    @Autowired
    private CardServiceImpl cardService;
    private BankAccount bankAccount;
    @Autowired
    private BankAccountRepository bankAccountRepository;


    @Test
    public void cardIsRegisteredSuccefully()
    {
        bankAccount = bankAccountRepository.getBankAccount("26922018960603293159613803");
        Card card = new Card("123122",1234,bankAccount);
        cardService.registerCard(bankAccount.getAccountNumber(),card);
        Card newCard = sqlCardRepository.getCard("123122");

        assertThat(newCard.getCardNumber().equals(card.getCardNumber()));
        assertThat(newCard.getBankAccount().equals(card.getBankAccount()));
        assertThat(newCard.getStatus().equals(card.getStatus()));
    }

    @Test
    public void isCardBlocked()
    {
        bankAccount = bankAccountRepository.getBankAccount("26922018960603293159613803");
        Card card = new Card("123123",1234,bankAccount);
        cardService.registerCard(bankAccount.getAccountNumber(),card);

        cardService.blockCard("123123");
        Card newCard = sqlCardRepository.getCard("123123");
        assertThat(newCard.getStatus().equals(BankEntityStatus.BLOCKED));
    }

    @Test
    public void isCardActivated()
    {
        bankAccount = bankAccountRepository.getBankAccount("26922018960603293159613803");
        Card card = new Card("123124",1234,bankAccount);
        cardService.registerCard(bankAccount.getAccountNumber(),card);
        cardService.activateCard(card);

        Card newCard = sqlCardRepository.getCard("123124");
        assertThat(newCard.getStatus().equals(BankEntityStatus.ACTIVE));
    }

    @Test
    public void doesPINChangingWork()
    {
        bankAccount = bankAccountRepository.getBankAccount("26922018960603293159613803");
        Card card = new Card("123125",1234,bankAccount);
        cardService.registerCard(bankAccount.getAccountNumber(),card);
        cardService.trySetNewPIN("123125",1234,4321);

        Card newCard = cardService.getCard("123125");
        int PIN = newCard.getPIN();
        int oldPIN = 1234;
        int newPIN = 4321;
        assertNotEquals(PIN,oldPIN);
        assertEquals(PIN,newPIN);
    }
}
