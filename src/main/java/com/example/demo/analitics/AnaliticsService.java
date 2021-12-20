package com.example.demo.analitics;

import com.example.demo.entities.bankAccount.BankAccount;
import com.example.demo.entities.bankAccount.BankAccountRepository;
import com.example.demo.entities.user.User;
import com.example.demo.entities.user.UserService;
import com.example.demo.transfers.TransferRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnaliticsService {

    UserService userService;
    BankAccountRepository bankAccountRepository;
    TransferRepository transferRepository;

    public AnaliticsService(UserService userService, BankAccountRepository bankAccountRepository, TransferRepository transferRepository) {
        this.userService = userService;
        this.bankAccountRepository = bankAccountRepository;
        this.transferRepository = transferRepository;
    }

    public Map<String, Object> generateUserSummaryReport(String userLogin) {
        User user = userService.getUser(userLogin);
        Map<String, Object> node = new HashMap<>();
        node.put("user", user.getLogin());
        List<BankAccount> bankAccountList = user.getAccounts();
        Map<String, Object> accounts = new HashMap<String, Object>();
        bankAccountList.forEach(e -> {
            accounts.put(e.getAccountNumber(), e.getBalance());
        });

        node.put("accounts", accounts);
        return node;
    }

    public void generateBankSummaryReport() {


    }


}
