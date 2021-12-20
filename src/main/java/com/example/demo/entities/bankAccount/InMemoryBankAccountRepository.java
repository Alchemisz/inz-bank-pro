package com.example.demo.entities.bankAccount;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBankAccountRepository implements BankAccountRepository{

    private Map<String, BankAccount> bankAccountsMap;

    public InMemoryBankAccountRepository() {
        bankAccountsMap = new ConcurrentHashMap<>();

        BankAccount bankAccount1 = new BankAccount("200040003000", new BigDecimal("100.24"));
        bankAccountsMap.put(bankAccount1.getAccountNumber(), bankAccount1);

        BankAccount bankAccount2 = new BankAccount("345623450984", new BigDecimal("864.72"));
        bankAccountsMap.put(bankAccount2.getAccountNumber(), bankAccount2);
    }

    @Override
    public BankAccount getBankAccount(String accountNUmber) {
        return bankAccountsMap.get(accountNUmber);
    }
}
