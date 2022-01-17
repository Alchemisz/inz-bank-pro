package com.example.demo.bankAccount.accountNumberGenerator;

import com.example.demo.bankAccount.BankAccountService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AccountNumberGenerator implements Generator{

    private final BankAccountService bankAccountService;

    public AccountNumberGenerator(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public synchronized String generate() {

        StringBuilder stringBuilder;
        Random random = new Random();

        do {
            stringBuilder = new StringBuilder();
            stringBuilder.append(random.nextInt(9) + 1);
            for (int i = 0; i < 25; i++) {
                stringBuilder.append(random.nextInt(10));
            }
        }while (bankAccountService.isAccountNumberExists(stringBuilder.toString()));

        return stringBuilder.toString();
    }
}
