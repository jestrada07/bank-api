package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    //HTTP method to retrieve ALL accounts
    @GetMapping("/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        logger.info("Successfully retrieved all accounts");
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);

    }

     //tested and works


    //HTTP method to get all accounts from a specific customer
    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<List<Account>> getAllAccountsByCustomerId(@PathVariable Long customerId) {
        logger.info("Fetched all accounts from customer ID #" + customerId);
        return new ResponseEntity<>(accountService.getAllAccountsByCustomerId(customerId), HttpStatus.OK);

    }

     //tested and works


    //HTTP method to get an account by id
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Optional<Account>> getAnAccountById(@PathVariable Long accountId) {
        logger.info("Fetched account ID #" + accountId);
        return new ResponseEntity<>(accountService.getAnAccountById(accountId), HttpStatus.OK);

    }

     //tested and works


    //HTTP method to create an account
    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Void> createAnAccount(@PathVariable Long customerId, @RequestBody Account accountToBeCreated) {
        accountService.createAnAccount(customerId, accountToBeCreated);
        logger.info("Successfully created an account for customer ID #" + customerId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

     //tested and works


    //Http method to update a specific existing account
    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Void> updateExistingAccount(@PathVariable Long customerId, @PathVariable Long accountId, @RequestBody Account accountToBeUpdated) {
        accountService.updateExistingAccount(customerId, accountId, accountToBeUpdated);
        logger.info("Successfully updated account ID #" + accountId + " from customer ID #" + customerId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

     //tested and works

    //HTTP method to delete a specific existing account
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<Object> deleteExistingAccount(@PathVariable Long accountId) {
//        accountService.deleteExistingAccount(accountId);
//        logger.info("Successfully deleted account ID #" + accountId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
            CodeFactorWithoutData success = new CodeFactorWithoutData(204, "Account #" + accountId + " successfully deleted!");
            accountService.deleteExistingAccount(accountId);
            logger.info("Successfully deleted Account #" + accountId + "!");
            return new ResponseEntity<>(success, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Account #" + accountId + " does not exist!");
            logger.info("Error! Cannot delete Account #" + accountId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

    } //tested and works


}
