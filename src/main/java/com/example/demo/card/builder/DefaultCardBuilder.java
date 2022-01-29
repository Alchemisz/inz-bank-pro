package com.example.demo.card.builder;

import com.example.demo.bankAccount.accountNumberGenerator.Generator;
import com.example.demo.card.Card;

public class DefaultCardBuilder implements CardBuilder{

    private final Card card;
    private final Generator cardNumberGenerator;

    public DefaultCardBuilder(Generator cardNumberGenerator) {
        this.cardNumberGenerator = cardNumberGenerator;
        this.card = new Card();
    }

    @Override
    public void buildCardNumber() {
        card.setCardNumber(cardNumberGenerator.generate());
    }

    @Override
    public Card getCard() {
        return card;
    }
}
