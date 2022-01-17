package com.example.demo.bankAccount.accountNumberGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountNumberGeneratorTest{

    @Autowired
    private AccountNumberGenerator accountNumberGenerator;

    @Test
    public void isShouldGenerateAccountNumber() {
        //given
        String generatedAccountNUmber;
        //when
        generatedAccountNUmber = accountNumberGenerator.generate();
        //then
        assertThat(generatedAccountNUmber).isNotBlank();
        assertThat(generatedAccountNUmber.length()).isEqualTo(26);
        assertThat(generatedAccountNUmber.charAt(0)).isNotEqualTo('0');
    }
}