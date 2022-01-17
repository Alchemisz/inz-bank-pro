package com.example.demo.transfers;

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
