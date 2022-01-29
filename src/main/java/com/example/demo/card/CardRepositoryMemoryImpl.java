package com.example.demo.card;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankEntityStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CardRepositoryMemoryImpl implements CardRepository{

    private final Map<String, Card> cardRepository;
    private final BankAccountRepository bankAccountRepository;


    public CardRepositoryMemoryImpl(BankAccountRepository bankAccountRepository) {
        this.cardRepository = new HashMap<>();
        this.bankAccountRepository = bankAccountRepository;

        BankAccount bankAccount1 = bankAccountRepository.getBankAccounts().get(0);
        Card card = new Card("7353390584888345", 1234, bankAccount1);
        card.setStatus(BankEntityStatus.ACTIVE);
        cardRepository.put(card.getCardNumber(), card);
        bankAccount1.addCard(card);

        Card card2 = new Card("2528931071787040", 6789, bankAccount1);
        cardRepository.put(card2.getCardNumber(), card2);
        bankAccount1.addCard(card2);

        BankAccount bankAccount2 = bankAccountRepository.getBankAccounts().get(1);
        Card card3 = new Card("8580149188994971", 3687, bankAccount1);
        cardRepository.put(card3.getCardNumber(), card3);
        bankAccount2.addCard(card3);

    }

    @Override
    public Card getCard(String cardNumber) {
        return cardRepository.get(cardNumber);
    }

    @Override
    public void addCard(Card card) {
        cardRepository.put(card.getCardNumber(), card);
    }

    @Override
    public void deleteCard(Card card) {
        cardRepository.remove(card.getCardNumber());
    }

    @Override
    public void update(Card card) {
        cardRepository.put(card.getCardNumber(), card);
    }
}
