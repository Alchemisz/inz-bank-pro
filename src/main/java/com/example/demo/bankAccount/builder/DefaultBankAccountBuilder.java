package com.example.demo.bankAccount.builder;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.accountNumberGenerator.Generator;

import java.math.BigDecimal;

public class DefaultBankAccountBuilder implements BankAccountBuilder{

    private BankAccount bankAccount;
    private final Generator accountNumberGenerator;

    public DefaultBankAccountBuilder(Generator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.bankAccount = new BankAccount();
    }

    @Override
    public void buildAccountNumber() {
        this.bankAccount.setAccountNumber(accountNumberGenerator.generate());
    }

    @Override
    public void buildAccountBalance() {
        this.bankAccount.setBalance(BigDecimal.valueOf(0.0));
    }

    @Override
    public void buildAccountCurrency() {
        this.bankAccount.setCurrency("PLN");
    }

    @Override
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }
}
