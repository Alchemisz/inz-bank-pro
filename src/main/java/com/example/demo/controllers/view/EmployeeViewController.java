package com.example.demo.controllers.view;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {

    private final BankAccountService bankAccountService;

    public EmployeeViewController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String management(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("userName", user.getLogin());
        model.addAttribute("userAccounts", bankAccountService.getBankAccounts(BankEntityStatus.INACTIVE, BankEntityStatus.BLOCKED));
        return "employee/accounts";
    }

    @GetMapping("/account")
    public String account(@RequestParam("accountNumber") String accountNumber, Model model) {
        BankAccount bankAccount = bankAccountService.getBankAccount(accountNumber);
        model.addAttribute("userAccounts", bankAccountService.getBankAccounts(BankEntityStatus.INACTIVE));
        bankAccount.setStatus(BankEntityStatus.ACTIVE);
        bankAccountService.update(bankAccount);
        return "redirect:/employee";
    }

}
