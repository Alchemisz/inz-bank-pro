package com.example.demo.bankAccount;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount getBankAccount(String accountNumber) {
        return bankAccountRepository.getBankAccount(accountNumber);
    }

    @Override
    public void registerBankAccount(BankAccount bankAccount) {
        bankAccountRepository.register(bankAccount);
    }

    @Override
    public boolean isAccountNumberExists(String accountNumber) {
        return (getBankAccount(accountNumber) != null);
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.getBankAccounts();
    }
}
