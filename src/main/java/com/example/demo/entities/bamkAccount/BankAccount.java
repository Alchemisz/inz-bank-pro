package com.example.demo.entities.bamkAccount;

import com.example.demo.entities.currency.Currency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Data
public class BankAccount {

    private String accountNumber;
    private BigDecimal balance;
    private String currency;

    public BankAccount(String accountNumber, BigDecimal balance, String currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

}
