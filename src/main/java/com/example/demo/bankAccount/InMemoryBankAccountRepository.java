package com.example.demo.bankAccount;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryBankAccountRepository implements BankAccountRepository{

    private Map<String, BankAccount> bankAccountsMap;

    public InMemoryBankAccountRepository() {
        bankAccountsMap = new ConcurrentHashMap<>();

        BankAccount bankAccount1 = new BankAccount("26922018960603293159613803", BankEntityStatus.ACTIVE, new BigDecimal("100.24"), "PLN");
        bankAccountsMap.put(bankAccount1.getAccountNumber(), bankAccount1);

        BankAccount bankAccount2 = new BankAccount("27926303620212481874123474", BankEntityStatus.ACTIVE, new BigDecimal("864.72"), "PLN");
        bankAccountsMap.put(bankAccount2.getAccountNumber(), bankAccount2);

        BankAccount bankAccount3 = new BankAccount("97751587375391814999941991", BankEntityStatus.ACTIVE, new BigDecimal("100.72"), "PLN");
        bankAccountsMap.put(bankAccount3.getAccountNumber(), bankAccount3);
    }

    @Override
    public BankAccount getBankAccount(String accountNUmber) {
        return bankAccountsMap.get(accountNUmber);
    }

    @Override
    public void register(BankAccount bankAccount) {
        bankAccountsMap.put(bankAccount.getAccountNumber(), bankAccount);
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return this.bankAccountsMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void update(BankAccount bankAccount) {
        bankAccountsMap.put(bankAccount.getAccountNumber(), bankAccount);
    }
}
