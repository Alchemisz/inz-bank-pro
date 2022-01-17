package com.example.demo.bankAccount;


import java.util.List;

public interface BankAccountRepository {

    BankAccount getBankAccount(String accountNUmber);
    void register(BankAccount bankAccount);
    List<BankAccount> getBankAccounts();
}
