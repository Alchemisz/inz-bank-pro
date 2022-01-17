package com.example.demo.user;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.security.priviledges.UserPriviledges;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class User {
    private String login;
    private String pass;
    private UserPriviledges userPriviledges;
    private List<BankAccount> accounts;

    public User(String login, String pass, UserPriviledges userPriviledges) {
        this.accounts = new LinkedList<>();

        this.login = login;
        this.pass = pass;
        this.userPriviledges = userPriviledges;
    }

    public void addAccount(BankAccount bankAccount){
        accounts.add(bankAccount);
    };

}
