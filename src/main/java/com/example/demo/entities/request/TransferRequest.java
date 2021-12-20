package com.example.demo.entities.request;

import com.example.demo.entities.bankAccount.BankAccount;

import java.math.BigDecimal;

public class TransferRequest implements RequestOrder{

    private BankAccount bankAccount;
    private BigDecimal amount;

    public TransferRequest(BankAccount bankAccount, BigDecimal amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        BigDecimal newBalance = bankAccount.getBalance().subtract(amount);
        bankAccount.setBalance(newBalance);
    }
}
