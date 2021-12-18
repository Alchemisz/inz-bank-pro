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
    private Currency currency;

    public BankAccount(String accountNumber, BigDecimal balance, Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public void setCurrency(Currency currency) {
        if (currency == null) return;
        //Do PLN
        BigDecimal multiplyResult = this.balance.multiply(BigDecimal.valueOf(this.currency.getToPLN()));
        //Z PLN na nową walutę
        BigDecimal newBalance = multiplyResult.multiply(BigDecimal.valueOf(currency.getFromPLN()));

        //Zaokrąglenie 2 miejsca po przecinku
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);

        this.balance = newBalance;
        this.currency = currency;
    }
}
