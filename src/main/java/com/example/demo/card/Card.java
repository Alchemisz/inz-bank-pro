package com.example.demo.card;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.card.prototype.Prototype;
import lombok.Data;

@Data
public class Card implements Prototype {

    private String cardNumber;
    private Integer PIN;
    private BankEntityStatus status;
    private BankAccount bankAccount;

    public Card(String cardNumber, Integer PIN, BankAccount bankAccount) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.bankAccount = bankAccount;
        this.status = BankEntityStatus.INACTIVE;
    }

    public Card() {
        this.status = BankEntityStatus.INACTIVE;
        this.PIN = 1111;
    }

    public Card(String cardNumber, BankAccount bankAccount) {
        this.cardNumber = cardNumber;
        this.bankAccount = bankAccount;
        this.status = BankEntityStatus.INACTIVE;
    }

    @Override
    public Prototype clone() {
        return new Card(cardNumber, bankAccount);
    }
}
