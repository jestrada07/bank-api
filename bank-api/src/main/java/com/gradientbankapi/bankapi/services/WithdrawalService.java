package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Withdrawal;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.WithdrawalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalService {

    @Autowired
    private WithdrawalRepo withdrawalRepo;

    @Autowired
    private AccountRepo accountRepo;

    //create a withdrawal
    public void createAWithdrawal(Long accountId, Withdrawal withdrawalToBeCreated) {
        Account account = accountRepo.findById(accountId).orElse(null);
        withdrawalToBeCreated.setAccount(account);
        withdrawalRepo.save(withdrawalToBeCreated);
    }

    //get all withdrawals for a specific account
    public List<Withdrawal> getAllWithdrawalsByAccountId(Long accountId) {
        return withdrawalRepo.findAllWithdrawalsByAccountId(accountId);
    }

    //get a withdrawal by id
    public Optional<Withdrawal> getAWithdrawalById(Long withdrawalId) {
        verifyWithdrawal(withdrawalId);
        return withdrawalRepo.findById(withdrawalId);
    }

    //update an existing withdrawal
    public void updateExistingWithdrawal(Long withdrawalId, Withdrawal withdrawalToBeUpdated) {
        verifyWithdrawal(withdrawalId);
        withdrawalToBeUpdated.setId(withdrawalId);
        withdrawalRepo.save(withdrawalToBeUpdated);
    }

    //delete an existing withdrawal
    public void deleteExistingWithdrawal(Long withdrawalId) {
        verifyWithdrawal(withdrawalId);
        withdrawalRepo.deleteById(withdrawalId);
    }

    protected void verifyWithdrawal(Long withdrawalId) throws ResourceNotFoundException {
        Optional<Withdrawal> withdrawal = withdrawalRepo.findById(withdrawalId);
        if(withdrawal.isEmpty()) {
            throw new ResourceNotFoundException("A withdrawal with an ID of #" + withdrawalId + " does not exist! :)");
        }
    }

}
