package com.example.demo.card;

import com.example.demo.bankAccount.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Card {

    private String cardNumber;
    private int PIN;
    private BankAccount bankAccount;

}
