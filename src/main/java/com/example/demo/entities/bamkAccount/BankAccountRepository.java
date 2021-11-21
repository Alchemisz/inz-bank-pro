package com.example.demo.entities.bamkAccount;


import java.util.Optional;

public interface BankAccountRepository {

    public Optional<BankAccount> getBankAccount(String accountNUmber);

}
