package com.example.demo.transfer;



import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferRepository;
import com.example.demo.transfers.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SqlTransferRepositoryTest {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TransferService transferService;


    @Test
    public void validAddAndGetTransfer()
    {
        Transfer transfer = new Transfer(transferService.getNextId(), "26922018960603293159613803", "27926303620212481874123474", new BigDecimal(40));
        transferRepository.addTransfer(transfer);
        assertNotNull(transferRepository.getTransfer(transfer.getId()));
    }

    @Test
    public void validAccountTransfer()
    {
        String receiverNow = "27926303620212481874123474";
        Transfer transfer = new Transfer(transferService.getNextId(), "26922018960603293159613803", receiverNow, new BigDecimal(40));
        transferRepository.addTransfer(transfer);
        String receiverId = transfer.getReceiverId();
        assertEquals(receiverId, receiverNow);
    }




}
