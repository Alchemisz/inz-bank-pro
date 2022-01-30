package com.example.demo.controllers.rest;

import com.example.demo.document.pdf.PDFGeneratorService;
import com.example.demo.request.RequestFactory;
import com.example.demo.request.RequestOrder;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferService;
import com.example.demo.verification.verificator.AbstractVerificator;
import com.example.demo.verification.verificator.VerificationType;
import com.example.demo.verification.verificator.VerificatorAbstractFactory;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class TransferController {

    private TransferService transferService;
    private RequestFactory requestFactory;
    private PDFGeneratorService pdfGeneratorService;
    private VerificatorAbstractFactory verificatorAbstractFactory;

    public TransferController(TransferService transferService, RequestFactory requestFactory, PDFGeneratorService pdfGeneratorService, VerificatorAbstractFactory verificatorAbstractFactory) {
        this.transferService = transferService;
        this.requestFactory = requestFactory;
        this.pdfGeneratorService = pdfGeneratorService;
        this.verificatorAbstractFactory = verificatorAbstractFactory;
    }

    @PostMapping("/transfer")
    public Map<String, String>transfer(@RequestBody JsonNode payload, SessionStatus sessionStatus)
    {
        sessionStatus.setComplete(); //Deleting account attribute from the session

        Map<String, String> response = new HashMap<>();
        String accountNumber = payload.get("accountNumber").toString().replace("\"","");
        String reciever = payload.get("reciever").toString().replace("\"","");
        BigDecimal amount = new BigDecimal(payload.get("amount").asDouble());
        System.out.println(accountNumber+" "+reciever+" "+amount);


        Transfer transfer = new Transfer(transferService.getNextId(), accountNumber, reciever, amount);

        RequestOrder transferRequest = requestFactory.createTransferRequest(transfer, transferService);
        AbstractVerificator transferVerificator = verificatorAbstractFactory.getTransferVerificator(VerificationType.EMAIL);

        String id = transferVerificator.startVerification(transferRequest);
        response.put("requestId", id);
        return response;
    }

    @GetMapping("/transfer/{transferId}")
    public void generateTransferConfirmationPDF(@PathVariable("transferId") String id, HttpServletResponse response){
        Transfer transfer = transferService.getTransferById(id);
        pdfGeneratorService.export(response, transfer);
    }
}
