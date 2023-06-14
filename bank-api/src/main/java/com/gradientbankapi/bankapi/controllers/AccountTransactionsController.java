package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.AccountTransactions;
import com.gradientbankapi.bankapi.services.AccountService;
import com.gradientbankapi.bankapi.services.AccountTransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AccountTransactionsController {

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionsController.class);

    @Autowired
    private AccountTransactionsService accountTransactionsService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/account-transactions/{accountId}")
    public ResponseEntity<Object> getAccountTransactions(@PathVariable Long accountId) {
        try {
            AccountTransactions accountTransactions = accountTransactionsService.getAccountTransactions(accountId);
            Optional<Account> accounts = accountService.getAnAccountById(accountId);

            if (accounts.isEmpty()) {
                CodeFactorWithoutData codeFactorWithoutData = new CodeFactorWithoutData(404, "This account with id #" + accountId + " does not exist!");
                logger.info("Error retrieving transactions for account #" + accountId);
                return new ResponseEntity<>(codeFactorWithoutData, HttpStatus.NOT_FOUND);
            }
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved transactions for account #" + accountId, accountTransactions);
            accountTransactionsService.getAccountTransactions(accountId);
            logger.info("Successfully retrieved transactions for account #" + accountId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "This account with id #" + accountId + " does not exist!");
            logger.info("Error retrieving transactions for account #" + accountId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

}
