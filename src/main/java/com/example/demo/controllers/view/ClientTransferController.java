package com.example.demo.controllers.view;

import com.example.demo.entities.bamkAccount.BankAccount;
import com.example.demo.entities.bamkAccount.BankAccountRepository;
import com.example.demo.entities.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class ClientTransferController {
    private String prefix = "/client/";
    private BankAccountRepository bankAccountRepository;

    public ClientTransferController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/transfer")
    public String transfer(HttpSession httpSession, Model model, @RequestParam("accountNumber") String accountNumber) {
        User user = (User) httpSession.getAttribute("user");
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        model.addAttribute("account", bankAccount);

        return prefix + "transfer";
    }

}
