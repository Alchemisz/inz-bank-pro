package com.example.demo.card;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.card.builder.CardDirector;
import com.example.demo.card.builder.directorFactory.CardDirectorFactory;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService{

    private CardRepository cardRepository;
    private BankAccountRepository bankAccountRepository;

    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void registerCard(String accountNumber, Card card) {
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        card.setBankAccount(bankAccount);
        bankAccount.addCard(card);
        cardRepository.addCard(card);
    }

    @Override
    public void unregisterCard(String cardNumber) {

    }

    @Override
    public void blockCard(String cardNumber) {
        Card card = getCard(cardNumber);
        card.setStatus(BankEntityStatus.BLOCKED);
        cardRepository.update(card);
    }

    @Override
    public void unblockCard(String cardNumber) {

    }

    @Override
    public boolean isCardNumberExists(String cardNumber) {
        return (cardRepository.getCard(cardNumber) != null);
    }

    @Override
    public Card getCard(String cardNumber) {
        return cardRepository.getCard(cardNumber);
    }

    @Override
    public void activateCard(Card card) {
        card.setStatus(BankEntityStatus.ACTIVE);
        cardRepository.update(card);

    }

    @Override
    public boolean trySetNewPIN(String cardId, Integer passedPin, Integer newPin) {
        Card card = cardRepository.getCard(cardId);
        if (card.getPIN().equals(passedPin)){
            card.setPIN(newPin);
            cardRepository.update(card);

            return true;
        }
        return false;
    }
}
