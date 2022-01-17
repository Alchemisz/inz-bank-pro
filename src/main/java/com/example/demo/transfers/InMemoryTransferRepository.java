package com.example.demo.transfers;

import com.example.demo.bankAccount.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryTransferRepository implements TransferRepository{
    Map<String, Transfer> transferMap;

    public InMemoryTransferRepository() {
        this.transferMap = new HashMap<String, Transfer>();
    }

    @Override
    public Transfer getTransfer(String transferId) {
        return transferMap.get(transferId);
    }

    @Override
    public void addTransfer(Transfer transfer) {
        transferMap.put(transfer.getId(), transfer);
    }

    @Override
    public List<Transfer> getAccountsTransfers(BankAccount bankAccount) {
        return transferMap.values().stream()
                .filter(e -> e.getSenderId().equals(bankAccount.getAccountNumber()) || e.getReceiverId().equals(bankAccount.getAccountNumber()))
                .collect(Collectors.toList());
    }
}
