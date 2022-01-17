package com.example.demo.bankAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
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
