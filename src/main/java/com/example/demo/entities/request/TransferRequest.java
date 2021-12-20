package com.example.demo.entities.request;

import com.example.demo.entities.transfers.Transfer;
import com.example.demo.transfers.TransferService;

import java.math.BigDecimal;

public class TransferRequest implements RequestOrder{

    private Transfer transfer;
    private TransferService transferService;

    public TransferRequest(Transfer transfer, TransferService transferService) {
        this.transfer = transfer;
        this.transferService = transferService;
    }

    @Override
    public void execute() {
        transferService.registerTransfer(transfer);
    }
}
