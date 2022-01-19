package com.example.demo.bankAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount getBankAccount(String accountNumber);
    void registerBankAccount(BankAccount bankAccount);
    boolean isAccountNumberExists(String accountNumber);
    List<BankAccount> getBankAccounts();
    List<BankAccount> getBankAccounts(BankAccountStatus... status);
    void update(BankAccount bankAccount);

    void blockAccount(String accountNumber);
}
