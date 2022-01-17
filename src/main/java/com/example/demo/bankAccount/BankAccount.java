package com.example.demo.bankAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class BankAccount {

    private String accountNumber;
    private BankAccountStatus status;
    private BigDecimal balance;
    private String currency;

    public BankAccount(String accountNumber, BankAccountStatus status, BigDecimal balance, String currency) {
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.currency = currency;
    }
}
