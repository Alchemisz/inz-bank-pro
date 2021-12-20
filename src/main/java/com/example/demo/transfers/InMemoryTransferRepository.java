package com.example.demo.transfers;

import com.example.demo.entities.transfers.Transfer;
import com.example.demo.entities.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Transfer> getUsersTransfers(User user) {
        return null;
    }
}
