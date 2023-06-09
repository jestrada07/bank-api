package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.models.Deposit;
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


    public void addAccount(Long customerId, Account account){
        //we need to find the customer by id
        Customer customer = customerRepo.findById(customerId).orElse(null);
        //setting the account as the customer's order
        account.setCustomer(customer);
        //save the account for customer with specific id
        accountRepo.save(account);}


    public Iterable<Account> getAllAccount(){

        return accountRepo.findAll(); //gets all accounts
    }

    public Optional<Account> getAccountById(Long accountId){
        verifyAccount(accountId);
        return accountRepo.findById(accountId); //gets account by ID

    }

    public void updateAccount(Long customerId,Long accountId, Account account){
        Customer customer = customerRepo.findById(customerId).orElse(null);
        Account a = accountRepo.findById(accountId).orElse(null);
        if( a != null){
            a.setId(account.getId());
            a.setBalance(account.getBalance());
            a.setRewards(account.getRewards());
            a.setNickname(account.getNickname());

        }
        account.setCustomer(customer);
        accountRepo.save(account);
    }

    public void deleteAccount(Long accountId){
        verifyAccount(accountId);
        accountRepo.deleteById(accountId);
        }

    protected void verifyAccount(Long accountId) throws ResourceNotFoundException {
        Optional<Account> account = accountRepo.findById(accountId);
        if(account.isEmpty()){
            throw new ResourceNotFoundException("The account with id " + accountId + " does not exist :(");
        }
    }




}
