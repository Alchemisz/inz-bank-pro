package com.example.demo.request;

import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.card.Card;
import com.example.demo.card.CardService;

public class ActivateCardRequest implements RequestOrder{

    private final CardService cardService;
    private final Card card;
    private final Integer pin;

    public ActivateCardRequest(CardService cardService, Card card, Integer pin) {
        this.cardService = cardService;
        this.card = card;
        this.pin = pin;
    }

    @Override
    public void execute() {
        card.setPIN(pin);
        cardService.activateCard(card);
    }
}
