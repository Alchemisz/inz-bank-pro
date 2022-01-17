package com.example.demo.bankAccount;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<BankAccount> getBankAccounts(BankAccountStatus status) {
        return bankAccountRepository.getBankAccounts()
                .stream()
                .filter(e -> e.getStatus().getTypeName().equals(status.getTypeName()))
                .collect(Collectors.toList());
    }
}
