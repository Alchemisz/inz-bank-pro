package com.example.demo.card.builder;

import com.example.demo.card.Card;

public class CardDirector {

    private final CardBuilder cardBuilder;

    public CardDirector(CardBuilder cardBuilder) {
        this.cardBuilder = cardBuilder;
    }

    private void makeCard(){
        cardBuilder.buildCardNumber();
    }

    public Card getCard(){
        this.makeCard();
        return cardBuilder.getCard();
    }
}
