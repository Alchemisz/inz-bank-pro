package com.example.demo.entities.currency;

import java.util.List;

public interface CurrencyRepository {

    Currency findById(String id);
    List<String> getCurrenciesCodes();

}
