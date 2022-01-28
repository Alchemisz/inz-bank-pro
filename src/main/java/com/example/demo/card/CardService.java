package com.example.demo.card;

import com.example.demo.bankAccount.BankAccount;

public interface CardService {
    void registerCard(String accounNumber);
    void unregisterCard(String cardNumber);
    void blockCard(String cardNumber);
    void unblockCard(String cardNumber);
    boolean isCardNumberExists(String cardNumber);
    Card getCard(String cardNumber);
    void activateCard(Card card);
    boolean trySetNewPIN(String cardId, Integer passedPin, Integer newPin);
}
