package com.example.demo.currency;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ApiCurrencyRepository implements CurrencyRepository{

    private List<String> currenciesCodes;

    public ApiCurrencyRepository() {
        currenciesCodes = new ArrayList<>();
        currenciesCodes = findById("USD").getExchangeRates().keySet().stream().collect(Collectors.toList());
        currenciesCodes.add("USD");

    }

    public List<String> getCurrenciesCodes(){
        return this.currenciesCodes;
    }

    @Override
    public Currency findById(String id) {
        Currency currency = null;
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://freecurrencyapi.net/api/v2/latest?apikey=1f114580-730a-11ec-8ee4-437bd0793e7e&base_currency="+id;

        try {
            URL urlForGetRequest = new URL(url);
            currency = mapper.readValue(urlForGetRequest, Currency.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currency;
    }

}
