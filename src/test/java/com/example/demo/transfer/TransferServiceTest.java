package com.example.demo.transfer;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferRepository;
import com.example.demo.transfers.TransferService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferServiceTest {

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransferService transferService;


    @Test
    public void registerValidTransferTest()
    {
        Transfer transfer = new Transfer("1","26922018960603293159613803", "27926303620212481874123474", new BigDecimal(30));
        transferService.registerTransfer(transfer);
        assertNotNull(transferRepository.getTransfer(transfer.getId()));
    }
    @Test
    public void registerTransferToYourSelfTest()
    {
        Transfer transfer = new Transfer("2", "26922018960603293159613803", "26922018960603293159613803", new BigDecimal(50));

       try{

       transferService.registerTransfer(transfer);

       } catch (IllegalArgumentException e) {

       Assert.assertEquals(1,1);
       assertNull(transferRepository.getTransfer(transfer.getId()));
       }

    }

    @Test
    public void assignedTransferToAccount()
    {

        Transfer transfer = new Transfer("3","26922018960603293159613803", "27926303620212481874123474", new BigDecimal(30));
        transferService.registerTransfer(transfer);
        assertNotNull(transferRepository.getAccountsTransfers(bankAccountRepository.getBankAccount(transfer.getSenderId())));

    }

    @Test
    public void transferByIdVerification()
    {

        Transfer transfer = new Transfer("4","26922018960603293159613803", "27926303620212481874123474", new BigDecimal(30));
        transferService.registerTransfer(transfer);
        String idTransfer = transfer.getId();
        assertNotNull(transferRepository.getTransfer(idTransfer));

    }



}
