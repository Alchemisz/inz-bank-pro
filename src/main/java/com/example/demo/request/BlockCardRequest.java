package com.example.demo.request;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.card.Card;
import com.example.demo.card.CardService;

public class BlockCardRequest implements RequestOrder{

    private final CardService cardService;
    private final String cardNumber;

    public BlockCardRequest(CardService cardService, String cardNumber) {
        this.cardService = cardService;
        this.cardNumber = cardNumber;
    }

    @Override
    public void execute() {
        cardService.blockCard(cardNumber);
    }
}
