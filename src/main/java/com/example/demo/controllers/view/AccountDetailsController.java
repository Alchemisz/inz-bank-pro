package com.example.demo.controllers.view;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.currency.StringWrapper;
import com.example.demo.transfers.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
@SessionAttributes({"account", "accountNumberWrapper"})
public class AccountDetailsController {

    private String prefix = "/client/";
    private BankAccountService bankAccountService;
    private TransferService transferService;

    public AccountDetailsController(BankAccountService bankAccountService, TransferService transferService) {
        this.bankAccountService = bankAccountService;
        this.transferService = transferService;
    }

    @ModelAttribute("accountNumberWrapper")
    public StringWrapper accountNumberWrapper(){
        return new StringWrapper();
    }

    @GetMapping("/account")
    public String account(@RequestParam("accountNumber") String accountNumber, Model model, @ModelAttribute("accountNumberWrapper") StringWrapper accountNumberSession) {
        BankAccount bankAccount = bankAccountService.getBankAccount(accountNumber);
        accountNumberSession.setValue(bankAccount.getAccountNumber());
        model.addAttribute("account", bankAccount);
        model.addAttribute("assignedTransfers", transferService.getAssignedTransfers(bankAccount));
        return prefix + "account";
    }

}
