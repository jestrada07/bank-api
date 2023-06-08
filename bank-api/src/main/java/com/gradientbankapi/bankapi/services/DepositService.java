package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;

    public void createDeposit(Deposit deposit){
        depositRepo.save(deposit);
    }

    public void updateDeposit(Long depositId, Deposit deposit){
        deposit.setId(depositId);
        depositRepo.save(deposit);
    }
}
