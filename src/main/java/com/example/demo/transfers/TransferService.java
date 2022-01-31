package com.example.demo.transfers;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.security.HashingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransferService {
    TransferRepository transferRepository;
    com.example.demo.card.CardService cardService;
    BankAccountService bankAccountService;
    HashingService hashingService;

    public TransferService(TransferRepository transferRepository, com.example.demo.card.CardService cardService, BankAccountService bankAccountService, HashingService hashingService) {
        this.transferRepository = transferRepository;
        this.cardService = cardService;
        this.bankAccountService = bankAccountService;
        this.hashingService = hashingService;
    }

    private String id = "1";

    public synchronized String getNextId() {
        String id = this.id;
        int intId = Integer.parseInt(id);
        this.id = (++intId) + "";
        return hashingService.hash(String.valueOf(new Date().getTime()));
    }
    public void registerTransfer(Transfer transfer) {
        System.out.println("próba transferu: " + transfer.getId() + " wysyłkowicz: " + transfer.getSenderId() + "odbiorca: " + transfer.getReceiverId());
        BankAccount sender = bankAccountService.getBankAccount(transfer.getSenderId());
        BankAccount receiver = bankAccountService.getBankAccount(transfer.getReceiverId());


        if(receiver != null) {
            if(receiver.getAccountNumber().equals(sender.getAccountNumber())){
                throw new IllegalArgumentException("Can't send transfer to yourself!");
            }
            BigDecimal newRecieverBalance = receiver.getBalance().add(transfer.getAmount());
            receiver.setBalance(newRecieverBalance);
            bankAccountService.update(receiver);
        } else {
            System.out.println("odbiorca zewnętrzny");
        }

        BigDecimal newSenderBalance = sender.getBalance().subtract(transfer.getAmount());
        sender.setBalance(newSenderBalance);
        bankAccountService.update(sender);

        transferRepository.addTransfer(transfer);
    }

    public List<Transfer> getAssignedTransfers(BankAccount bankAccount){
        return transferRepository.getAccountsTransfers(bankAccount);
    }

    public Transfer getTransferById(String id){
        return transferRepository.getTransfer(id);
    }

    public List<Transfer> getMarkedTransfers() {
        return null;
    }
    public boolean verifyTransfer() {
        return false;
    }
}
