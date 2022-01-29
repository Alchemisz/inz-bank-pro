package com.example.demo.request;

import com.example.demo.card.Card;
import com.example.demo.card.CardService;
import com.example.demo.card.builder.CardDirector;
import com.example.demo.card.builder.directorFactory.CardDirectorFactory;

public class CreateCardRequest implements RequestOrder {

    private CardService cardService;
    private String accountNumber;
    private final CardDirector cardDirector;

    public CreateCardRequest(CardService cardService, String accountNumber, CardDirector cardDirector) {
        this.cardService = cardService;
        this.accountNumber = accountNumber;
        this.cardDirector = cardDirector;
    }

    @Override
    public void execute() {
        cardService.registerCard(accountNumber, cardDirector.getCard());
    }
}
