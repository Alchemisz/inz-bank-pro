package com.example.demo.bankAccount.builder;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.bankAccount.accountNumberGenerator.Generator;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DefaultBankAccountBuilder implements BankAccountBuilder{

    private BankAccount bankAccount;
    private final Generator accountNumberGenerator;

    public DefaultBankAccountBuilder(@Qualifier("accountNumberGenerator") Generator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.bankAccount = new BankAccount();
        this.bankAccount.setCardList(new ArrayList<>());
    }

    @Override
    public void buildAccountNumber() {
        this.bankAccount.setAccountNumber(accountNumberGenerator.generate());
        this.bankAccount.setStatus(BankEntityStatus.INACTIVE);
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
    public void buildCard() {
        // TODO add implementation
    }

    @Override
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }
}
