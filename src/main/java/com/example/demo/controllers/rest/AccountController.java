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
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

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
        bankAccount.setUser(user);
        RequestOrder bankAccountRequest = requestFactory.createBankAccountRequest(bankAccount, user, bankAccountService);
        AbstractVerificator bankAccountVerificator = verificatorAbstractFactory.getCreateBankAccountVerificator(VerificationType.EMAIL);
        String id = bankAccountVerificator.startVerification(bankAccountRequest);
        Map<String, String> response = new HashMap<>();
        response.put("requestId", id);
        return response;
    }

    @PostMapping("/accounts/block")
    public Map<String, String> createBlockRequest(@RequestBody JsonNode payload){
        String accountNumber = payload.get("accountNumber").toString().replace("\"", "");
        RequestOrder blockBankAccountRequest = requestFactory.createBlockBankAccountRequest(bankAccountService, bankAccountService.getBankAccount(accountNumber));
        AbstractVerificator blockBankAccountVerificator = verificatorAbstractFactory.getBlockBankAccountVerificator(VerificationType.EMAIL);
        String id = blockBankAccountVerificator.startVerification(blockBankAccountRequest);
        Map<String, String> response = new HashMap<>();
        response.put("requestId", id);
        return response;
    }


}







