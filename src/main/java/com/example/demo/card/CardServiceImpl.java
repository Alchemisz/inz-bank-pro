package com.example.demo.card;

import com.example.demo.bankAccount.BankEntityStatus;
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

    @Override
    public boolean isCardNumberExists(String cardNumber) {
        return (cardRepository.getCard(cardNumber) != null);
    }

    @Override
    public Card getCard(String cardNumber) {
        return cardRepository.getCard(cardNumber);
    }

    @Override
    public void activateCard(Card card) {
        card.setStatus(BankEntityStatus.ACTIVE);
        //TODO UPDATE W BAZIE
    }

    @Override
    public boolean trySetNewPIN(String cardId, Integer passedPin, Integer newPin) {
        Card card = cardRepository.getCard(cardId);
        if (card.getPIN().equals(passedPin)){
            card.setPIN(newPin);
            //TODO UPDATE W BAZIE
            return true;
        }
        return false;
    }
}
