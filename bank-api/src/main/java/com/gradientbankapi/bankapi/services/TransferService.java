package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Transfer;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.TransferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    AccountRepo accountRepo;

    public Transfer transferingAmount(Long sendCustAccount, Long receiveCustAccount, Transfer transfer){
        Account sender = accountRepo.findById(sendCustAccount).orElse(null);
        Account reciever = accountRepo.findById(receiveCustAccount).orElse(null);

        sender.setBalance(sender.getBalance() - transfer.getAmount());
        if (sender.getBalance() < transfer.getAmount()) {
            throw new IllegalStateException("The account with id " + sender + " has insufficient balance for this withdrawal :(");
        }
        accountRepo.save(sender);

        reciever.setBalance(reciever.getBalance() + transfer.getAmount());
        accountRepo.save(reciever);

        return transferRepo.save(transfer);
    }

    
}
