package com.example.demo.currency;

import java.util.List;

public interface CurrencyRepository {

    Currency findById(String id);
    List<String> getCurrenciesCodes();

}
