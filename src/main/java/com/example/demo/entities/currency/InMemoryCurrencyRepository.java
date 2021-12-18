package com.example.demo.entities.currency;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCurrencyRepository implements CurrencyRepository{

    private List<Currency> currencies;

    public InMemoryCurrencyRepository() {

        currencies = new ArrayList<>();
        currencies.add(new Currency("PLN", 1, 1));
        currencies.add(new Currency("EUR", 4.63, 0.22));
        currencies.add(new Currency("USD", 4.12, 0.24));

    }

    @Override
    public Currency findById(String id) {
        //Czemu tu strumień nie działa????
        for (Currency currency : currencies){
            if (currency.getName().equals(id)) return currency;
        }
        return null;
    }

    @Override
    public List<Currency> findAll() {
        return currencies;
    }
}
