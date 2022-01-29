package com.example.demo.bankAccount;

import com.example.demo.user.User;

import java.util.List;

public interface BankAccountService {

    BankAccount getBankAccount(String accountNumber);
    void registerBankAccount(BankAccount bankAccount);
    boolean isAccountNumberExists(String accountNumber);
    List<BankAccount> getBankAccounts();
    List<BankAccount> getUserAccountsForStatus(User user, BankEntityStatus... status);
    void update(BankAccount bankAccount);

    void blockAccount(String accountNumber);

    void activateBankAccount(String accountNumber);
}
