package com.example.demo.bankAccount.builder.directorFactory;

import com.example.demo.bankAccount.builder.BankAccountDirector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankAccountDirectorFactoryTest {

    @Autowired
    private BankAccountDirectorFactory bankAccountDirectorFactory;

    @Test
    public void shouldGetBankAccountDirector(){
        BankAccountDirector bankAccountDirector = bankAccountDirectorFactory.getBankAccountDirector();
        assertThat(bankAccountDirector).isInstanceOf(BankAccountDirector.class);
    }

}