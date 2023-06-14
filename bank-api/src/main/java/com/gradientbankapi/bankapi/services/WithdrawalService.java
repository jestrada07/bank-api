package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.models.Withdrawal;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.WithdrawalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.gradientbankapi.bankapi.enums.StatusType.COMPLETED;
import static com.gradientbankapi.bankapi.enums.StatusType.PENDING;

@Service
public class WithdrawalService {

    @Autowired
    private WithdrawalRepo withdrawalRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Transactional
    public Withdrawal createAWithdrawal(Long accountId, Withdrawal withdrawalToBeCreated) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("The account with id " + accountId + " does not exist :("));

        // Check if account has enough balance to make the withdrawal
        if (account.getBalance() < withdrawalToBeCreated.getAmount()) {
            throw new IllegalStateException("The account with id " + accountId + " has insufficient balance for this withdrawal :(");
        }

        account.setBalance(account.getBalance() - withdrawalToBeCreated.getAmount()); // Decrease account balance by the withdrawal amount
        accountRepo.save(account); // Save updated account to the database

        withdrawalToBeCreated.setAccount(account);
        changeDefault();
        return withdrawalRepo.save(withdrawalToBeCreated);
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
    public Withdrawal updateExistingWithdrawal(Long withdrawalId, Withdrawal withdrawalUpdate) {
        Withdrawal originalWithdrawal = withdrawalRepo.findById(withdrawalId)
                .orElseThrow(() -> new ResourceNotFoundException("A withdrawal with an ID of #" + withdrawalId + " does not exist! :)"));

        Account account = originalWithdrawal.getAccount();

        if(withdrawalUpdate.getAmount() != originalWithdrawal.getAmount()) {
            // First, revert the original withdrawal
            account.setBalance(account.getBalance() + originalWithdrawal.getAmount());

            // Then, apply the updated withdrawal
            if(account.getBalance() < withdrawalUpdate.getAmount()) {
                throw new IllegalStateException("The account with id " + account.getId() + " has insufficient balance for this withdrawal :(");
            }

            account.setBalance(account.getBalance() - withdrawalUpdate.getAmount());
            originalWithdrawal.setAmount(withdrawalUpdate.getAmount());
            accountRepo.save(account);
        }

        if(withdrawalUpdate.getType() != null) {
            originalWithdrawal.setType(withdrawalUpdate.getType());
        }
        if(withdrawalUpdate.getTransaction_date() != null) {
            originalWithdrawal.setTransaction_date(withdrawalUpdate.getTransaction_date());
        }
        if(withdrawalUpdate.getStatus() != null) {
            originalWithdrawal.setStatus(withdrawalUpdate.getStatus());
        }
        if(withdrawalUpdate.getPayer_id() != null) {
            originalWithdrawal.setPayer_id(withdrawalUpdate.getPayer_id());
        }
        if(withdrawalUpdate.getMedium() != null) {
            originalWithdrawal.setMedium(withdrawalUpdate.getMedium());
        }
        if(withdrawalUpdate.getDescription() != null) {
            originalWithdrawal.setDescription(withdrawalUpdate.getDescription());
        }

        return withdrawalRepo.save(originalWithdrawal);
    }

    //delete an existing withdrawal
    public void deleteExistingWithdrawal(Long withdrawalId) {
        Withdrawal originalWithdrawal = withdrawalRepo.findById(withdrawalId)
                .orElseThrow(() -> new ResourceNotFoundException("A withdrawal with an ID of #" + withdrawalId + " does not exist! :)"));
        Account account = originalWithdrawal.getAccount();
        //reverts back to its original balance
        account.setBalance(account.getBalance() + originalWithdrawal.getAmount());
        withdrawalRepo.deleteById(withdrawalId);
    }

    protected void verifyWithdrawal(Long withdrawalId) throws ResourceNotFoundException {
        Optional<Withdrawal> withdrawal = withdrawalRepo.findById(withdrawalId);
        if(withdrawal.isEmpty()) {
            throw new ResourceNotFoundException("A withdrawal with an ID of #" + withdrawalId + " does not exist! :)");
        }
    }

    @Scheduled(fixedRate = 9000)
    protected void changeDefault() {
        Iterable<Withdrawal> d = withdrawalRepo.findAll();
        for (Withdrawal check : d) {
            if (check.getStatus() == PENDING) {
                check.setStatus(COMPLETED);
                withdrawalRepo.save(check);
            }
        }
    }

}
