package com.example.demo.transfers;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class Transfer {
    private final String id;
    private final String senderId;
    private final String receiverId;
    private final BigDecimal amount;
    private Date transferDate;

    public Transfer(String id, String senderId, String receiverId, BigDecimal amount) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        this.transferDate = Date.valueOf(dateTimeFormatter.format(LocalDateTime.now()));
    }
}
