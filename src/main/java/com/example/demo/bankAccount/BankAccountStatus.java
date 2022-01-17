package com.example.demo.bankAccount;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
public enum BankAccountStatus {
    BLOCKED("Blocked"),
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String typeName;

    BankAccountStatus(String typeName) {
        this.typeName = typeName;
    }
}
