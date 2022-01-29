package com.example.demo.bankAccount;

import com.example.demo.card.Card;
import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BankAccount {
    private int id;
    private String accountNumber;
    private BankEntityStatus status;
    private BigDecimal balance;
    private String currency;
    private List<Card> cardList;
    private User user;

    public BankAccount(String accountNumber, BankEntityStatus status, BigDecimal balance, String currency) {
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.currency = currency;
        this.cardList = new ArrayList<>();

    }

    public void addCard(Card card){
        cardList.add(card);
    }
}
