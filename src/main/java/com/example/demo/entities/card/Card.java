package com.example.demo.entities.card;

import com.example.demo.entities.bankAccount.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Card {

    private String cardNumber;
    private int PIN;
    private BankAccount bankAccount;

}
