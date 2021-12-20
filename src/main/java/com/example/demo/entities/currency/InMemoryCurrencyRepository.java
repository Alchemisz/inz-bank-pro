package com.example.demo.entities.currency;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCurrencyRepository implements CurrencyRepository{

    private List<Currency> currencies;

    public InMemoryCurrencyRepository() {

        currencies = new ArrayList<>();
        currencies.add(new Currency("PLN", new BigDecimal(1.0), new BigDecimal(1.0)));
        currencies.add(new Currency("EUR", new BigDecimal(4.63), new BigDecimal(0.2159)));
        currencies.add(new Currency("USD", new BigDecimal(4.12), new BigDecimal(0.2427)));

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
