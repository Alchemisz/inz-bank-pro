package com.example.demo.card;

public interface CardService {
    void registerCard(Card card);
    void unregisterCard(String cardNumber);
    void blockCard(String cardNumber);
    void unblockCard(String cardNumber);
}
