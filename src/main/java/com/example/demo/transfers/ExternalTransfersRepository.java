package com.example.demo.transfers;

public interface ExternalTransfersRepository {

    public void addTransfer(Transfer transfer);
    public Transfer getTransfer(String transferId);

}
