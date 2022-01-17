package com.example.demo.currency;

import com.example.demo.bankAccount.BankAccount;

import java.util.List;

public interface CurrencyService {

    Currency findById(String id);
    List<String> findCurrenciesCodes(String id);
    void convertAccountToNewCurrency(BankAccount bankAccount, String newCurrencyCode);
    List<String> getCurrenciesCodes();
    List<String> getCurrenciesWithoutCode(String code);
}
