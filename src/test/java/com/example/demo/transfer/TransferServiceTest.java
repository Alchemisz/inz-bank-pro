package com.example.demo.transfer;

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

       Assert.assertThat(transferService.registerTransfer(transfer), Throws.);




    }



}
