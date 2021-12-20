package com.example.demo.controllers.view;

import com.example.demo.entities.bankAccount.BankAccount;
import com.example.demo.entities.bankAccount.BankAccountRepository;
import com.example.demo.entities.user.User;
import com.example.demo.transfers.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class AccountDetailsController {
    private String prefix = "/client/";
    private BankAccountRepository bankAccountRepository;
    private TransferService transferService;

    public AccountDetailsController(BankAccountRepository bankAccountRepository, TransferService transferService) {
        this.bankAccountRepository = bankAccountRepository;
        this.transferService = transferService;
    }

    @GetMapping("/account")
    public String account(HttpSession httpSession, Model model, @RequestParam("accountNumber") String accountNumber) {
        User user = (User) httpSession.getAttribute("user");
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        model.addAttribute("account", bankAccount);
        model.addAttribute("assignedTransfers", transferService.getAssignedTransfers(bankAccount));
        return prefix + "account";
    }

}
