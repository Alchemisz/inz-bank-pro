package com.example.demo.request;

import com.example.demo.card.CardService;
import com.example.demo.user.User;

public class CreateCardRequest implements RequestOrder {

    private CardService cardService;
    private User user;

    public CreateCardRequest(CardService cardService, User user) {
        this.cardService = cardService;
        this.user = user;
    }

    @Override
    public void execute() {

    }
}
