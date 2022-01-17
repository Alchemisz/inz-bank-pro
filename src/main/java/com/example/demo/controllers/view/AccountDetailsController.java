package com.example.demo.controllers.view;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.transfers.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
@SessionAttributes("account")
public class AccountDetailsController {

    private String prefix = "/client/";
    private BankAccountService bankAccountService;
    private TransferService transferService;

    public AccountDetailsController(BankAccountService bankAccountService, TransferService transferService) {
        this.bankAccountService = bankAccountService;
        this.transferService = transferService;
    }

    @GetMapping("/account")
    public String account(@RequestParam("accountNumber") String accountNumber, Model model) {
        model.addAttribute("account", bankAccountService.getBankAccount(accountNumber));
        BankAccount bankAccount = bankAccountService.getBankAccount(accountNumber);
        model.addAttribute("assignedTransfers", transferService.getAssignedTransfers(bankAccount));
        return prefix + "account";
    }

}
