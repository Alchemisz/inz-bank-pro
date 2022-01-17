package com.example.demo.transfers;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.card.CardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    TransferRepository transferRepository;
    CardService cardService;
    BankAccountService bankAccountService;

    public TransferService(TransferRepository transferRepository, CardService cardService, BankAccountService bankAccountService) {
        this.transferRepository = transferRepository;
        this.cardService = cardService;
        this.bankAccountService = bankAccountService;
    }

    private String id = "1";

    public synchronized String getNextId() {
        String id = this.id;
        int intId = Integer.parseInt(id);
        this.id = (++intId) + "";
        return id;
    }
    public void registerTransfer(Transfer transfer) {
        //System.out.println("próba transferu: " + transfer.getId() + " wysyłkowicz: " + transfer.getSenderId() + "odbiorca: " + transfer.getReceiverId());
        BankAccount sender = bankAccountService.getBankAccount(transfer.getSenderId());
        BankAccount reciever = bankAccountService.getBankAccount(transfer.getReceiverId());

        if(reciever.getAccountNumber().equals(sender.getAccountNumber())){
            throw new IllegalArgumentException("Can't send transfer to yourself!");
        }

        if(reciever != null) {

            BigDecimal newRecieverBalance = reciever.getBalance().add(transfer.getAmount());
            BigDecimal newSenderBalance = sender.getBalance().subtract(transfer.getAmount());
            sender.setBalance(newSenderBalance);
            reciever.setBalance(newRecieverBalance);
        } else {
            System.out.println("odbiorca zewnętrzny");
        }
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
