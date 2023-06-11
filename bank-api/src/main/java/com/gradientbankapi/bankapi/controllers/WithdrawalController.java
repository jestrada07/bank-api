package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.models.Withdrawal;
import com.gradientbankapi.bankapi.services.WithdrawalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WithdrawalController {

    private static final Logger logger = LoggerFactory.getLogger(WithdrawalController.class);

    @Autowired
    private WithdrawalService withdrawalService;

    //HTTP method to get all withdrawals for a specific account
    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {
        logger.info("Fetched all withdrawals from account ID #" + accountId);
        return new ResponseEntity<>(withdrawalService.getAllWithdrawalsByAccountId(accountId), HttpStatus.OK);
    } //tested and works

    //HTTP method to get a withdrawal by id
    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Optional<Withdrawal>> getAWithdrawalById(@PathVariable Long withdrawalId) {
        logger.info("Fetched withdrawal ID #" + withdrawalId);
        return new ResponseEntity<>(withdrawalService.getAWithdrawalById(withdrawalId), HttpStatus.OK);
    } //tested and works

    //HTTP method to create a withdrawal
    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<Void> createAWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawalToBeCreated) {
        withdrawalService.createAWithdrawal(accountId, withdrawalToBeCreated);
        logger.info("Successfully created a withdrawal for account ID #" + accountId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    } //tested and works

    //HTTP method to update a specific existing withdrawal
    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Void> updateExistingWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawalToBeUpdated) {
        withdrawalService.updateExistingWithdrawal(withdrawalId, withdrawalToBeUpdated);
        logger.info("Successfully updated withdrawal ID #" + withdrawalId);
        return new ResponseEntity<>(HttpStatus.OK);
    } //tested and works

    //HTTP method to delete an existing withdrawal
    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Void> deleteExistingWithdrawal(@PathVariable Long withdrawalId) {
        withdrawalService.deleteExistingWithdrawal(withdrawalId);
        logger.info("Successfully deleted withdrawal ID #" + withdrawalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } //tested and works

}
