package com.example.demo.bankAccount.builder;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.bankAccount.builder.directorFactory.BankAccountDirectorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultBankAccountBuilderTest {

    @Autowired
    private BankAccountDirectorFactory bankAccountDirectorFactory;

    private BankAccountDirector bankAccountDirector;

    @Before
    public void init() {
        bankAccountDirector = bankAccountDirectorFactory.getBankAccountDirector();
    }

    @Test
    public void shouldBuildDefaultBankAccount(){
        BankAccount bankAccount = bankAccountDirector.getBankAccount();

        assertThat(bankAccount).isInstanceOf(BankAccount.class);
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(0.0));
        assertThat(bankAccount.getStatus().getTypeName()).isEqualTo(BankEntityStatus.INACTIVE.getTypeName());
        assertThat(bankAccount.getCardList().isEmpty()).isTrue();
        assertThat(bankAccount.getUser()).isNull();
    }

}