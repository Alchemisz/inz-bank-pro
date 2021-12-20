package com.example.demo.transfers;

import com.example.demo.entities.transfers.Transfer;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryExternalTransfersRepostioryImplementation implements ExternalTransfersRepository {

    @Override
    public void addTransfer(Transfer transfer) {
        
    }

    @Override
    public Transfer getTransfer(String transferId) {
        return null;
    }
}
