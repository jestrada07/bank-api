package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Iterable<Account> getAllAccountsByCustomerId(Long customerId) {
        return accountRepo.findAllAccountsByCustomerId(customerId);
    }

    //get an account by id
    public Optional<Account> getAnAccountById(Long accountId) {
        return accountRepo.findById(accountId);
    }

    //create an account
    public Account createAnAccount(Long customerId, Account accountToBeCreated) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        accountToBeCreated.setCustomer(customer);
      return   accountRepo.save(accountToBeCreated);
    }

    //update a specific existing account
    public Account updateExistingAccount(Long customerId, Long accountId, Account accountToBeUpdated) {
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
       return accountRepo.save(accountToBeUpdated);
    }

    //delete a specific existing account
    public void deleteExistingAccount(Long accountId) {
        verifyAccount(accountId);
        accountRepo.deleteById(accountId);
    }

    protected void verifyAccount(Long accountId) throws ResourceNotFoundException {
        Optional<Account> account = accountRepo.findById(accountId);
        if(account.isEmpty()) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error!");
            throw new ResourceNotFoundException(error.getMessage());
        }
    }

}
