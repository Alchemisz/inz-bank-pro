package com.example.demo.document.pdf;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.bankAccount.builder.directorFactory.BankAccountDirectorFactory;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
public class PDFGeneratorServiceTest {

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    private HttpServletResponse response;

    @Autowired
    private TransferService transferService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountDirectorFactory bankAccountDirectorFactory;

    @Autowired
    private UserService userService;

    private Transfer transfer;

    @Before
    public void init(){
        User test = userService.getUser("test");
        BankAccount bankAccount1 = bankAccountDirectorFactory.getBankAccountDirector().getBankAccount();
        bankAccount1.setBalance(BigDecimal.valueOf(100));
        BankAccount bankAccount2 = bankAccountDirectorFactory.getBankAccountDirector().getBankAccount();

        bankAccount1.setUser(test);
        bankAccount2.setUser(test);
        bankAccountService.registerBankAccount(bankAccount1);
        bankAccountService.registerBankAccount(bankAccount2);

        transfer = new Transfer(transferService.getNextId(), bankAccount1.getAccountNumber(),
                bankAccount2.getAccountNumber(), BigDecimal.valueOf(50));
        response = new MockHttpServletResponse();
    }

    @Test
    public void shouldExportPdf(){
        pdfGeneratorService.export(response, transfer);

        assertThat(response.getContentType()).isEqualTo("application/pdf");
        assertThat(response.getHeaders("Content-Disposition").stream().collect(Collectors.toList()).get(0))
                .isEqualTo("inline; filename=transfer_" + transfer.getId() + "_" + transfer.getTransferDate() + ".pdf");

    }

}