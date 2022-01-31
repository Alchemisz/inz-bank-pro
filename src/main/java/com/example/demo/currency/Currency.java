package com.example.demo.currency;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Currency {

    private String currencyName;

//    @JsonAlias("data") //Dla key1
    @JsonAlias("conversion_rates") //Dla key2
    private Map<String, Double> exchangeRates;

    public Currency() {
    }

    public Currency(String name) {
        this.currencyName = name;
    }

    @JsonProperty("query")
    private void unpackNested(Map<String, String> query){
        this.currencyName = query.get("base_currency");
    }
//    @JsonProperty("query") //Dla key1
//    private void unpackNested(Map<String, String> query){
//        this.currencyName = query.get("base_currency");
//    }

}

