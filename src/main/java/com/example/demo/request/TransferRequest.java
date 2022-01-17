package com.example.demo.request;

import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferService;

public class TransferRequest implements RequestOrder{

    private Transfer transfer;
    private TransferService transferService;

    public TransferRequest(Transfer transfer, TransferService transferService) {
        this.transfer = transfer;
        this.transferService = transferService;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    @Override
    public void execute() {
        transferService.registerTransfer(transfer);
    }
}
