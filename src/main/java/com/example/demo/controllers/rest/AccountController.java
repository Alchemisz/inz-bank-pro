package com.example.demo.controllers.rest;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.bankAccount.builder.directorFactory.BankAccountDirectorFactory;
import com.example.demo.request.RequestFactory;
import com.example.demo.request.RequestOrder;
import com.example.demo.user.User;
import com.example.demo.verification.verificator.AbstractVerificator;
import com.example.demo.verification.verificator.VerificationType;
import com.example.demo.verification.verificator.VerificatorAbstractFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    private BankAccountService bankAccountService;
    private RequestFactory requestFactory;
    private VerificatorAbstractFactory verificatorAbstractFactory;
    private BankAccountDirectorFactory bankAccountDirectorFactory;

    public AccountController(BankAccountService bankAccountService, RequestFactory requestFactory, VerificatorAbstractFactory verificatorAbstractFactory, BankAccountDirectorFactory bankAccountDirectorFactory) {
        this.bankAccountService = bankAccountService;
        this.requestFactory = requestFactory;
        this.verificatorAbstractFactory = verificatorAbstractFactory;
        this.bankAccountDirectorFactory = bankAccountDirectorFactory;
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

    @PostMapping("/accounts")
    public Map<String, String> createBankAccountRequest(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        BankAccount bankAccount = bankAccountDirectorFactory.getBankAccountDirector().getBankAccount();
        RequestOrder bankAccountRequest = requestFactory.createBankAccountRequest(bankAccount, user, bankAccountService);
        AbstractVerificator bankAccountVerificator = verificatorAbstractFactory.getCreateBankAccountVerificator(VerificationType.EMAIL);
        String id = bankAccountVerificator.startVerification(bankAccountRequest);
        System.out.println("id: " + id);
        Map<String, String> response = new HashMap<>();
        response.put("requestId", id);
        return response;
    }



}







