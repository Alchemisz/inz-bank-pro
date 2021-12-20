package com.example.demo.entities.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Currency {

    private String name;
    private BigDecimal toPLN;
    private BigDecimal fromPLN;

    public Currency(String name, BigDecimal toPLN, BigDecimal fromPLN) {
        this.name = name;
        this.toPLN = toPLN;
        this.fromPLN = fromPLN;
    }

}
