package com.example.demo.entities.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Currency {

    private String name;
    private double toPLN;
    private double fromPLN;

    public Currency(String name, double toPLN, double fromPLN) {
        this.name = name;
        this.toPLN = toPLN;
        this.fromPLN = fromPLN;
    }

}
