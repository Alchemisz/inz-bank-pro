package com.example.demo.entities.card;

import com.example.demo.entities.request.RequestOrder;
import com.example.demo.entities.user.User;

public class CreateCardRequest implements RequestOrder {

    private CardService cardService;
    private User user;

    @Override
    public void execute() {

    }
}
