package com.example.demo.request;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.user.User;
import lombok.Getter;

@Getter
public class CreateBankAccountRequest implements RequestOrder{

    private final BankAccount bankAccount;
    private final User user;
    private final BankAccountService bankAccountService;

    public CreateBankAccountRequest(BankAccount bankAccount, User user, BankAccountService bankAccountService) {
        this.bankAccount = bankAccount;
        this.user = user;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void execute() {
        user.addAccount(bankAccount);
        bankAccountService.registerBankAccount(bankAccount);
    }
}
