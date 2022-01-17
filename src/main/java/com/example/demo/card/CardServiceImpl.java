package com.example.demo.card;

import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService{

    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void registerCard(Card card) {

    }

    @Override
    public void unregisterCard(String cardNumber) {

    }

    @Override
    public void blockCard(String cardNumber) {

    }

    @Override
    public void unblockCard(String cardNumber) {

    }
}
