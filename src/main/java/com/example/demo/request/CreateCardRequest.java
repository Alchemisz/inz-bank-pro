package com.example.demo.request;

import com.example.demo.card.CardService;

public class CreateCardRequest implements RequestOrder {

    private CardService cardService;
    private String accountNumber;

    public CreateCardRequest(CardService cardService, String bankAccount) {
        this.cardService = cardService;
        this.accountNumber = bankAccount;
    }

    @Override
    public void execute() {
        cardService.registerCard(accountNumber);
    }
}
