package com.example.demo.bankAccount.builder;

import com.example.demo.bankAccount.BankAccount;

public interface BankAccountBuilder {

    void buildAccountNumber();
    void buildAccountBalance();
    void buildAccountCurrency();
    BankAccount getBankAccount();
}
