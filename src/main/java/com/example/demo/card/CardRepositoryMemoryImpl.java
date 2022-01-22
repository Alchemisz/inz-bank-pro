package com.example.demo.card;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
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
        bankAccount1.addCard(new Card("1234", 1234, bankAccount1));
        bankAccount1.addCard(new Card("6789", 6789, bankAccount1));

        BankAccount bankAccount2 = bankAccountRepository.getBankAccounts().get(1);
        bankAccount2.addCard(new Card("3687", 3687, bankAccount1));

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
}
