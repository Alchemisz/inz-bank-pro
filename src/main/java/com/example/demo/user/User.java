package com.example.demo.user;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.security.priviledges.UserPriviledges;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private int id;
    private String login;
    private String pass;
    private UserPriviledges userPriviledges;
    private List<BankAccount> accounts;
    private boolean blocked;


    public User(String login, String pass, UserPriviledges userPriviledges) {
        this.accounts = new LinkedList<>();
        this.blocked = false;
        this.login = login;
        this.pass = pass;
        this.userPriviledges = userPriviledges;
    }

    public void addAccount(BankAccount bankAccount){
        accounts.add(bankAccount);
    };

}
