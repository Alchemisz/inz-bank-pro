package com.example.demo.service.currency;

import com.example.demo.entities.currency.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAll();
    Currency findById(String id);


}
