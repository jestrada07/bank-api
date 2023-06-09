package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;

    public void createDeposit(Deposit deposit){
        depositRepo.save(deposit);
    }

    public Optional<Deposit> getDepositById(Long depositId){
        verifyDeposit(depositId);
        return depositRepo.findById(depositId);
    }

    public Optional<Deposit> getDepositsForAccount(Long accountId){
        verifyDeposit(accountId);
        return depositRepo.findByAccount(accountId);
    }

    public void updateDeposit(Long depositId, Deposit deposit){
        verifyDeposit(depositId);
        deposit.setId(depositId);
        depositRepo.save(deposit);
    }

    public void deleteDeposit(Long depositId){
        verifyDeposit(depositId);
        depositRepo.deleteById(depositId);
    }

    protected void verifyDeposit(Long depositId) throws ResourceNotFoundException{
        Optional<Deposit> deposit = depositRepo.findById(depositId);
        if(deposit.isEmpty()){
            throw new ResourceNotFoundException("The deposit with id " + depositId + " does not exist :(");
        }
    }
}
