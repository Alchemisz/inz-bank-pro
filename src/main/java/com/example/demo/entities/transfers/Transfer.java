package com.example.demo.entities.transfers;

import java.math.BigDecimal;

public class Transfer {
    private final String id;
    private final String senderId;
    private final String receiverId;
    private final BigDecimal amount;

    public Transfer(String id, String senderId, String receiverId, BigDecimal amount) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }
}
