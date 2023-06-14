package com.gradientbankapi.bankapi.services;
import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.*;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import com.gradientbankapi.bankapi.repos.TransferRepo;
import com.gradientbankapi.bankapi.repos.WithdrawalRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTransactionsService {

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionsService.class);

    @Autowired
    private DepositRepo depositRepo;

    @Autowired
    private WithdrawalRepo withdrawalRepo;

    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    private AccountRepo accountRepo;

    public AccountTransactions getAccountTransactions(Long accountId) {
        verifyTransaction(accountId);
        List<Deposit> deposits = depositRepo.findAllDepositsByAccountId(accountId);
        List<Withdrawal> withdrawals = withdrawalRepo.findAllWithdrawalsByAccountId(accountId);
        List<Transfer> transfers = transferRepo.findAllBySendingCustomerOrReceivingCustomer(accountId,accountId);

        return new AccountTransactions(deposits, withdrawals, transfers);
    }

    protected void verifyTransaction(Long accountId) throws ResourceNotFoundException {
        Optional<Account> accountTransactions = accountRepo.findById(accountId);
        if(accountTransactions.isEmpty()){
            throw new ResourceNotFoundException("The account with id# " + accountId + " does not exist :(");
        }
    }

}