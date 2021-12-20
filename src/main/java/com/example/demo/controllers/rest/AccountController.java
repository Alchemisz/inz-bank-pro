package com.example.demo.controllers.rest;

import com.example.demo.entities.bankAccount.BankAccount;
import com.example.demo.entities.bankAccount.BankAccountRepository;
import com.example.demo.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public AccountController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAccounts(HttpSession httpSession){
            User user = (User) httpSession.getAttribute("user");
            if (user != null){
                return user.getAccounts();
            }else {
                return null;
            }
    }



}
