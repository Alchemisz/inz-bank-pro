package com.example.demo.card.builder.directorFactory;

import com.example.demo.bankAccount.accountNumberGenerator.Generator;
import com.example.demo.card.builder.CardDirector;
import com.example.demo.card.builder.DefaultCardBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CardDirectorFactory {

    private final Generator cardNumberGenerator;

    public CardDirectorFactory(@Qualifier("cardNumberGenerator") Generator cardNumberGenerator) {
        this.cardNumberGenerator = cardNumberGenerator;
    }

    public CardDirector getCardDirector(){
        return new CardDirector(new DefaultCardBuilder(cardNumberGenerator));
    }

}
