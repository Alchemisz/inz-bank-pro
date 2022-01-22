package com.example.demo.bankAccount;

import lombok.Getter;

@Getter
public enum BankEntityStatus {
    BLOCKED("Blocked"),
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String typeName;

    BankEntityStatus(String typeName) {
        this.typeName = typeName;
    }
}
