package com.example.demo.transfers;

import com.example.demo.entities.transfers.Transfer;

public interface ExternalTransfersRepository {

    public void addTransfer(Transfer transfer);
    public Transfer getTransfer(String transferId);

}
