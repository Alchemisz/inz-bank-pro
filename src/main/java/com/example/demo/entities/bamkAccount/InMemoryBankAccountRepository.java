package com.example.demo.entities.bamkAccount;

import com.example.demo.entities.currency.InMemoryCurrencyRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBankAccountRepository implements BankAccountRepository{

    private Map<String, BankAccount> bankAccountsMap;


    public InMemoryBankAccountRepository(InMemoryCurrencyRepository currencyRepository) {
        bankAccountsMap = new ConcurrentHashMap<>();

        BankAccount bankAccount1 = new BankAccount("200040003000", new BigDecimal("100.24"), currencyRepository.findById("PLN"));
        bankAccountsMap.put(bankAccount1.getAccountNumber(), bankAccount1);

        BankAccount bankAccount2 = new BankAccount("345623450984", new BigDecimal("864.72"), currencyRepository.findById("PLN"));
        bankAccountsMap.put(bankAccount2.getAccountNumber(), bankAccount2);
    }

    @Override
    public BankAccount getBankAccount(String accountNUmber) {
        return bankAccountsMap.get(accountNUmber);
    }
}
