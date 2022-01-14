package com.example.demo.entities.currency;

import com.example.demo.entities.bamkAccount.BankAccount;
import com.example.demo.entities.currency.Currency;

import java.util.List;

public interface CurrencyService {

    Currency findById(String id);
    List<String> findCurrenciesCodes(String id);
    void convertAccountToNewCurrency(BankAccount bankAccount, String newCurrencyCode);
    List<String> getCurrenciesCodes();
    List<String> getCurrenciesWithoutCode(String code);
}
