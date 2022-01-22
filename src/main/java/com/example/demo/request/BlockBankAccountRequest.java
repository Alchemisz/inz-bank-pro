package com.example.demo.request;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;

public class BlockBankAccountRequest implements RequestOrder{

    private final BankAccountService bankAccountService;
    private final BankAccount bankAccount;

    public BlockBankAccountRequest(BankAccountService bankAccountService, BankAccount bankAccount) {
        this.bankAccountService = bankAccountService;
        this.bankAccount = bankAccount;
    }

    @Override
    public void execute() {
        bankAccountService.blockAccount(bankAccount.getAccountNumber());
    }
}
