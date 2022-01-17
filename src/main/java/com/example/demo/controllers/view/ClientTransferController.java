package com.example.demo.controllers.view;

import com.example.demo.bankAccount.BankAccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/client")
@SessionAttributes("account")
public class ClientTransferController {

    private String prefix = "/client/";
    private BankAccountRepository bankAccountRepository;

    public ClientTransferController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/transfer")
    public String transfer() {
        return prefix + "transfer";
    }

}
