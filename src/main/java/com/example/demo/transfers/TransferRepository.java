package com.example.demo.transfers;

import com.example.demo.bankAccount.BankAccount;

import java.util.List;

public interface TransferRepository {
    public Transfer getTransfer(String transferId);
    public void addTransfer(Transfer transfer);
    public List<Transfer> getAccountsTransfers(BankAccount bankAccount);

}
