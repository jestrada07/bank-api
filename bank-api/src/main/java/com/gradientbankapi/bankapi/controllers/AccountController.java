package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    //HTTP method to retrieve ALL accounts
    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        Iterable<Account> accounts = accountService.getAllAccounts();
        if (accounts.iterator().hasNext()) {
            CodeMessageFactor successfullResponse = new CodeMessageFactor(200,
                    "Successfully retrieved all accounts!", accounts);
            return new ResponseEntity<>(successfullResponse, HttpStatus.OK);
        }
        CodeFactorWithoutData failedResponse = new CodeFactorWithoutData(404, "Error fetching all accounts");
        return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
    } //tested and works

    //HTTP method to get all accounts from a specific customer
    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsByCustomerId(@PathVariable Long customerId) {
        Iterable<Account> accounts = accountService.getAllAccountsByCustomerId(customerId);
        if (accounts.iterator().hasNext()) {
            CodeMessageFactor successfullResponse = new CodeMessageFactor(200,
                    "Successfully retrieved all accounts from customer #" + customerId + "!", accounts);
            logger.info("Successfully retrieved all accounts from customer #" + customerId + "!");
            return new ResponseEntity<>(successfullResponse, HttpStatus.OK);
        }
        CodeFactorWithoutData failedResponse = new CodeFactorWithoutData(404,
                "Error fetching accounts! Customer #" + customerId + " does not exist!");
        logger.info("Error fetching accounts! Customer #" + customerId + " does not exist!");
        return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
    } //tested and works

    //HTTP method to get an account by id
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Object> getAnAccountById(@PathVariable Long accountId) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved customer account #" + accountId, accountService.getAnAccountById(accountId));
            logger.info("Successfully retrieved customer account #" + accountId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Customer account #" + accountId + " does not exist!");
            logger.info("Error! Customer account #" + accountId + " does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //HTTP method to create an account
    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Object> createAnAccount(@PathVariable Long customerId, @RequestBody Account accountToBeCreated) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(201, "Customer account created", accountService.createAnAccount(customerId, accountToBeCreated));
            logger.info("Successfully created a customer!");
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400, "Error creating customer");
            logger.info("Error! Cannot create customer!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //tested and works

    //Http method to update a specific existing account
    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Object> updateExistingAccount(@PathVariable Long customerId, @PathVariable Long accountId, @RequestBody Account accountToBeUpdated) {
        try {
            CodeFactorWithoutData success = new CodeFactorWithoutData(202, "Customer account #" + accountId + " updated successfully!");
            logger.info("Account #" + accountId + " updated successfully!");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Account #" + accountId + " does not exist!");
            logger.info("Error! Cannot update Account #" + accountId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //HTTP method to delete a specific existing account
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteExistingAccount(@PathVariable Long accountId) {
        try {
            //CodeFactorWithoutData success = new CodeFactorWithoutData(200, "Account #" + accountId + " successfully deleted!");
            accountService.deleteExistingAccount(accountId);
            logger.info("Successfully deleted Account #" + accountId + "!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Account #" + accountId + " does not exist!");
            logger.info("Error! Cannot delete Account #" + accountId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

}






