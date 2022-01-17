package com.example.demo.bankAccount;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankAccountServiceTest {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void accountNumberShouldExists(){
        //Given
        List<BankAccount> bankAccounts = bankAccountRepository.getBankAccounts();
        //when
        boolean accountNumberExists = bankAccountService.isAccountNumberExists(bankAccounts.get(0).getAccountNumber());
        //then
        assertThat(accountNumberExists).isTrue();
    }

    @Test
    public void accountNumberShouldNotExists(){
        //Given
        String unExistingAccountNUmber = new String("0".repeat(26));
        //when
        boolean accountNumberExists = bankAccountService.isAccountNumberExists(unExistingAccountNUmber);
        //then
        assertThat(unExistingAccountNUmber.length()).isEqualTo(26);
        assertThat(accountNumberExists).isFalse();
    }
}