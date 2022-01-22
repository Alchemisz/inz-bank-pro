package com.example.demo.card;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankEntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Card {

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
}
