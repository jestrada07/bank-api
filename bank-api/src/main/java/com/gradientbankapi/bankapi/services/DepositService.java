package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DepositService {

    @Autowired
    DepositRepo depositRepo;
    @Autowired
    AccountRepo accountRepo;


    public Deposit createDeposit(Long accountId, Deposit depositToBeCreated){
        Account account = accountRepo.findById(accountId).orElse(null);

        if (account == null) {
            throw new ResourceNotFoundException("The account with id " + accountId + " does not exist :(");
        }

        account.setBalance(account.getBalance() + depositToBeCreated.getAmount()); // Increase account balance by the deposit amount
        accountRepo.save(account); // Save updated account to the database

        depositToBeCreated.setAccount(account); // Link the account with the deposit
        return depositRepo.save(depositToBeCreated);
    }


    public Optional<Deposit> getDepositById(Long depositId){
        verifyDeposit(depositId);
        return depositRepo.findById(depositId);
    }

    public List<Deposit> getDepositsForAccount(Long accountId){
        verifyDeposit(accountId);
        return depositRepo.findAllDepositsByAccountId(accountId);
    }


    public Deposit updateDeposit(Long depositId, Deposit depositUpdate){
        Deposit originalDeposit = depositRepo.findById(depositId)
                .orElseThrow(() -> new ResourceNotFoundException("The originalDeposit with id " + depositId + " does not exist :("));

        Account account = originalDeposit.getAccount();

        if(depositUpdate.getAmount() != originalDeposit.getAmount()) {


            // First, revert the original deposit
            account.setBalance(account.getBalance() - originalDeposit.getAmount());

            // Then, apply the updated deposit
            account.setBalance(account.getBalance() + depositUpdate.getAmount());

            originalDeposit.setAmount(depositUpdate.getAmount());
            accountRepo.save(account);

        }

        if(depositUpdate.getType() != null) {
            originalDeposit.setType(depositUpdate.getType());
        }
        if(depositUpdate.getTransaction_date() != null) {
            originalDeposit.setTransaction_date(depositUpdate.getTransaction_date());
        }
        if(depositUpdate.getStatus() != null) {
            originalDeposit.setStatus(depositUpdate.getStatus());
        }
        if(depositUpdate.getPayee_id() != null) {
            originalDeposit.setPayee_id(depositUpdate.getPayee_id());
        }
        if(depositUpdate.getMedium() != null) {
            originalDeposit.setMedium(depositUpdate.getMedium());
        }
        if(depositUpdate.getDescription() != null) {
            originalDeposit.setDescription(depositUpdate.getDescription());
        }

        return depositRepo.save(originalDeposit);
    }


    public void deleteDeposit(Long depositId){
        Deposit originalDeposit = depositRepo.findById(depositId)
                .orElseThrow(() -> new ResourceNotFoundException("The deposit with an ID of #" + depositId + " does not exist :("));
        Account account = originalDeposit.getAccount();
        //reverts back to its original balance
        account.setBalance(account.getBalance() - originalDeposit.getAmount());
        depositRepo.deleteById(depositId);
    }

    protected void verifyDeposit(Long depositId) throws ResourceNotFoundException{
        Optional<Deposit> deposit = depositRepo.findById(depositId);
        if(deposit.isEmpty()){
            throw new ResourceNotFoundException("The deposit with id " + depositId + " does not exist :(");
        }
    }


}
