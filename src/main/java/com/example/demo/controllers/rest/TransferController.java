package com.example.demo.controllers.rest;

import com.example.demo.entities.bankAccount.BankAccount;
import com.example.demo.entities.bankAccount.BankAccountRepository;
import com.example.demo.entities.request.TransferRequest;
import com.example.demo.verification.VerificationService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class TransferController {

    private VerificationService verificationService;
    private BankAccountRepository bankAccountRepository;

    public TransferController(VerificationService verificationService, BankAccountRepository bankAccountRepository) {
        this.verificationService = verificationService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @PostMapping("/transfer")
    public Map<String, String>transfer(@RequestBody JsonNode payload)
    {
        Map<String, String> response = new HashMap<>();
        String accountNumber = payload.get("accountNumber").toString().replace("\"","");
        String reciever = payload.get("reciever").toString().replace("\"","");
        BigDecimal amount = new BigDecimal(payload.get("amount").asDouble());
        System.out.println(accountNumber+" "+reciever+" "+amount);
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        TransferRequest transferRequest = new TransferRequest(bankAccount,amount);
        String id = verificationService.registerTransferRequest(transferRequest);
        response.put("requestId", id);
        return response;
    }
}
