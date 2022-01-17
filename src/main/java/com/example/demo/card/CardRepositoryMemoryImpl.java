package com.example.demo.card;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CardRepositoryMemoryImpl implements CardRepository{

    Map<String, Card> cardRepository;

    @Override
    public Card getCard(String cardNumber) {
        return null;
    }

    @Override
    public void addCard(Card card) {

    }

    @Override
    public void deleteCard(Card card) {

    }
}
