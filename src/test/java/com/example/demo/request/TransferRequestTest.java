package com.example.demo.request;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferRepository;
import com.example.demo.transfers.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferRequestTest {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    TransferService transferService;

    @Autowired
    RequestFactory requestFactory;

    @Test
    public void validTransferRequestTest() {
        BankAccount sender = bankAccountService.getBankAccount("26922018960603293159613803");
        BankAccount reciever = bankAccountService.getBankAccount("27926303620212481874123474");
        BigDecimal ammount = new BigDecimal(10);
        Transfer transfer = new Transfer(transferService.getNextId(), sender.getAccountNumber(), reciever.getAccountNumber(), ammount);

        BigDecimal senderBalance = sender.getBalance();
        BigDecimal recieverBalance = reciever.getBalance();

        RequestOrder transferRequest = requestFactory.createTransferRequest(transfer, transferService);

        transferRequest.execute();

        assertNotNull(transferService.getTransferById(transfer.getId()));

        sender = bankAccountService.getBankAccount("26922018960603293159613803");
        reciever = bankAccountService.getBankAccount("27926303620212481874123474");
        assertEquals(sender.getBalance(), senderBalance.subtract(ammount));
        assertEquals(reciever.getBalance(), recieverBalance.add(ammount));
    }

}
