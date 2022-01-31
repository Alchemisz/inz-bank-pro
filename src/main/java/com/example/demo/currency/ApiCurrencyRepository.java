package com.example.demo.currency;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ApiCurrencyRepository implements CurrencyRepository{

    private List<String> currenciesCodes;

    private final String key1 = "https://v6.exchangerate-api.com/v6/d80ee2fe846c182d625bb90c/latest/";
    private final String key2 = "https://freecurrencyapi.net/api/v2/latest?apikey=1f114580-730a-11ec-8ee4-437bd0793e7e&base_currency=";

    public ApiCurrencyRepository() {
        currenciesCodes = new ArrayList<>();
        currenciesCodes = findById("USD").getExchangeRates().keySet().stream().collect(Collectors.toList());
        if (!currenciesCodes.contains("USD"))
            currenciesCodes.add("USD");

    }

    public List<String> getCurrenciesCodes(){
        return this.currenciesCodes;
    }

    @Override
    public Currency findById(String id) {
        Currency currency = null;
        ObjectMapper mapper = new ObjectMapper();
        String url = key1 + id;

        try {
            URL urlForGetRequest = new URL(url);
            currency = mapper.readValue(urlForGetRequest, Currency.class);
        } catch (IOException e) {
            Map<String, Double> exchangeRates = new HashMap<>();
            exchangeRates.put("USD", 0.43);
            exchangeRates.put("PLN", 0.43);
            currency = new Currency("USD");
            currency.setExchangeRates(exchangeRates);


        } finally {
            return currency;
        }

    }

}
