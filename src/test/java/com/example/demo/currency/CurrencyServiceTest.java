package com.example.demo.currency;

import com.example.demo.bankAccount.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    BankAccount bankAccount;

    @PostConstruct
    private void initialize(){
        bankAccount = new BankAccount("7833456458945", new BigDecimal("100.00"), "PLN");
    }

    @Test
    public void itShouldConvertBankAccountToNewCurrency(){
        //given
        String newCurrencyCode = "EUR";
        Double newCurrencyRate = currencyService.findById("PLN").getExchangeRates().get(newCurrencyCode);
        BigDecimal newBalance = bankAccount.getBalance().multiply(BigDecimal.valueOf(newCurrencyRate));
        //when
        currencyService.convertAccountToNewCurrency(bankAccount, newCurrencyCode);
        //then
        assertThat(bankAccount.getCurrency()).isEqualTo(newCurrencyCode);
        assertThat(bankAccount.getBalance()).isEqualTo(newBalance);

    }

    @Test
    public void itShouldNotFindCurrencyByCode(){
        //given
        String unexistingCode = "ZZZ";
        //when
        Currency currency = currencyService.findById(unexistingCode);
        //then
        assertThat(currency).isNull();
    }

    @Test
    public void itShouldFindCurrencyByCode(){
        //given
        String existingCode = "EUR";
        //when
        Currency currency = currencyService.findById(existingCode);
        //then
        assertThat(currency.getCurrencyName()).isEqualTo(existingCode);
    }

    @Test
    public void itShouldFindCurrenciesCodesWithoutPassedCode(){
        //given
        String existingCode = "EUR";
        //when
        List<String> currency = currencyService.getCurrenciesWithoutCode(existingCode);
        List<String> emptyList = currency.stream().filter(e -> e.equals(existingCode)).collect(Collectors.toList());
        //then
        assertThat(emptyList.isEmpty()).isTrue();
    }

    @Test
    public void itShouldGetAllCurrenciesCodes(){
        //given
        List<String> currenciesCodes = currencyService.getCurrenciesWithoutCode("USD");
        //when
        currenciesCodes.add("USD");
        //then
        assertThat(currenciesCodes).isEqualTo(currencyService.getCurrenciesCodes());
    }

}