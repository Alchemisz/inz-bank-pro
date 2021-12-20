package com.example.demo.transfers;

import com.example.demo.entities.bankAccount.BankAccount;
import com.example.demo.entities.transfers.Transfer;
import com.example.demo.entities.user.User;

import java.util.List;

public interface TransferRepository {
    public Transfer getTransfer(String transferId);
    public void addTransfer(Transfer transfer);
    public List<Transfer> getAccountsTransfers(BankAccount bankAccount);

}
