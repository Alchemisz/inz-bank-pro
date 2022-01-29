package com.example.demo.controllers.view;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.currency.StringWrapper;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {

    private final BankAccountService bankAccountService;
    private final UserService userService;

    public EmployeeViewController(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @GetMapping("/search")
    public String searchForUsers(@RequestParam(value = "search") Optional<String> username, Model model){
            List<User> users = new ArrayList<>();
        if (username.isPresent())
            users = userService.findMatches(username.get());
        model.addAttribute("users", users);
        return "employee/search";
    }

    @GetMapping("/accounts/activation")
    public String management(@RequestParam("user") String username, @RequestParam("accountNumber") String accountNumber){
        bankAccountService.activateBankAccount(accountNumber);
        return "redirect:?user="+username;
    }

    @GetMapping("/accounts")
    public String account(@RequestParam("user") String username, Model model) {
        model.addAttribute("username", new StringWrapper(username));
        model.addAttribute("userAccounts", bankAccountService.getUserAccountsForStatus(userService.getUser(username), BankEntityStatus.INACTIVE, BankEntityStatus.BLOCKED));
        return "employee/accounts";
    }

}
