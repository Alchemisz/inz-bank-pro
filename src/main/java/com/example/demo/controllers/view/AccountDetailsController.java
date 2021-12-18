package com.example.demo.controllers.view;

import com.example.demo.entities.bamkAccount.BankAccount;
import com.example.demo.entities.bamkAccount.BankAccountRepository;
import com.example.demo.entities.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class AccountDetailsController {
    private String prefix = "/client/";
    private BankAccountRepository bankAccountRepository;

    public AccountDetailsController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/account")
    public String account(HttpSession httpSession, Model model, @RequestParam("accountNumber") String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        model.addAttribute("account", bankAccount);

        return prefix + "account";
    }

}
