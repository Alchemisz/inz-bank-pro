package com.example.demo.controllers.view;

import com.example.demo.entities.bankAccount.BankAccount;
import com.example.demo.entities.bankAccount.BankAccountRepository;
import com.example.demo.entities.transfers.Transfer;
import com.example.demo.entities.user.User;
import com.example.demo.transfers.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client")
@SessionAttributes("account")
public class AccountDetailsController {

    private String prefix = "/client/";
    private BankAccountRepository bankAccountRepository;
    private TransferService transferService;

    public AccountDetailsController(BankAccountRepository bankAccountRepository, TransferService transferService) {
        this.bankAccountRepository = bankAccountRepository;
        this.transferService = transferService;
    }

    @ModelAttribute(name = "account")
    public BankAccount bankAccount(@RequestParam("accountNumber") String accountNumber){
        return bankAccountRepository.getBankAccount(accountNumber);
    }

    @ModelAttribute(name = "assignedTransfers")
    public List<Transfer> assignedTransfers(@RequestParam("accountNumber") String accountNumber){
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        return transferService.getAssignedTransfers(bankAccount);
    }

    @GetMapping("/account")
    public String account() {
        return prefix + "account";
    }

}
