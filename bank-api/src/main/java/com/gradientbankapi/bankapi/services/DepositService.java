package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;
    @Autowired
    AccountRepo accountRepo;

    public void createDeposit(Long accountId, Deposit depositToBeCreated ){
        Account account = accountRepo.findById(accountId).orElse(null);
        depositToBeCreated.setAccount(account);
        depositRepo.save(depositToBeCreated);
    }

    public Optional<Deposit> getDepositById(Long depositId){
        verifyDeposit(depositId);
        return depositRepo.findById(depositId);
    }

    public List<Deposit> getDepositsForAccount(Long accountId){
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
