package com.example.demo.bankAccount.builder.directorFactory;

import com.example.demo.bankAccount.accountNumberGenerator.Generator;
import com.example.demo.bankAccount.builder.BankAccountDirector;
import com.example.demo.bankAccount.builder.DefaultBankAccountBuilder;
import org.springframework.stereotype.Component;

@Component
public class BankAccountDirectorFactory {

    private Generator accountNumberGenerator;

    public BankAccountDirectorFactory(Generator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public BankAccountDirector getBankAccountDirector(){
        return new BankAccountDirector(new DefaultBankAccountBuilder(accountNumberGenerator));
    }

}
