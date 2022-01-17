package com.example.demo.card;

public interface CardRepository {

    Card getCard(String cardNumber);
    void addCard(Card card);
    void deleteCard(Card card);

}
