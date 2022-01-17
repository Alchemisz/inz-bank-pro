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

    @JsonAlias("data")
    private Map<String, Double> exchangeRates;

    public Currency() {
    }

    @JsonProperty("query")
    private void unpackNested(Map<String, String> query){
        this.currencyName = query.get("base_currency");
    }

}

