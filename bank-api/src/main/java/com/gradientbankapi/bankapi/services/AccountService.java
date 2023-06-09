package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private CustomerRepo customerRepo;

    //get all accounts
    public Iterable<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    //get all accounts from a specific customer
    public List<Account> getAllAccountsByCustomerId(Long customerId) {
        return accountRepo.findAllAccountsByCustomerId(customerId);
    }

    //get an account by id
    public Optional<Account> getAnAccountById(Long accountId) {
        verifyAccount(accountId);
        return accountRepo.findById(accountId);
    }

    //create an account
    public void createAnAccount(Long customerId, Account accountToBeCreated) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        accountToBeCreated.setCustomer(customer);
        accountRepo.save(accountToBeCreated);
    }

    //update a specific existing account
    public void updateExistingAccount(Long customerId, Long accountId, Account accountToBeUpdated) {
        verifyAccount(accountId);
        Customer customer = customerRepo.findById(customerId).orElse(null);
        Account account = accountRepo.findById(accountId).orElse(null);
        if(account != null) {
            account.setType(accountToBeUpdated.getType());
            account.setNickname(accountToBeUpdated.getNickname());
            account.setRewards(accountToBeUpdated.getRewards());
            account.setBalance(accountToBeUpdated.getBalance());
        }
        accountToBeUpdated.setCustomer(customer);
        accountRepo.save(accountToBeUpdated);
    }

    //delete a specific existing account
    public void deleteExistingAccount(Long accountId) {
        verifyAccount(accountId);
        accountRepo.deleteById(accountId);
    }

    protected void verifyAccount(Long accountId) throws ResourceNotFoundException {
        Optional<Account> account = accountRepo.findById(accountId);
        if(account.isEmpty()) {
            throw new ResourceNotFoundException("An account with an ID of #" + accountId + " does not exist! :/");
        }
    }

}
