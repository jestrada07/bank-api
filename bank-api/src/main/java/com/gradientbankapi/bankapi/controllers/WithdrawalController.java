package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Withdrawal;
import com.gradientbankapi.bankapi.services.WithdrawalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WithdrawalController {

    private static final Logger logger = LoggerFactory.getLogger(WithdrawalController.class);

    @Autowired
    private WithdrawalService withdrawalService;

    //HTTP method to get all withdrawals for a specific account
    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<Object> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {
        List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalsByAccountId(accountId);
        if(withdrawals.isEmpty()) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Cannot retrieve all withdrawals; Account #" + accountId + " does not exist!");
            logger.info("Error! Cannot retrieve all withdrawals; Account #" + accountId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageFactor success = new CodeMessageFactor(HttpStatus.OK.value(),
                "Successfully retrieved all withdrawals from account #" + accountId, withdrawals);
        logger.info("Successfully retrieved all withdrawals from account #" + accountId);
        return new ResponseEntity<>(success, HttpStatus.OK);
    } //tested and works

    //HTTP method to get a withdrawal by id
    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Object> getAWithdrawalById(@PathVariable Long withdrawalId) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved withdrawal #" + withdrawalId,
                    withdrawalService.getAWithdrawalById(withdrawalId));
            logger.info("Successfully retrieved withdrawal #" + withdrawalId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Withdrawal #" + withdrawalId + " does not exist!");
            logger.info("Error! Withdrawal #" + withdrawalId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //HTTP method to create a withdrawal
    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<Object> createAWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawalToBeCreated) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(201, "Withdrawal created successfully!",
                    withdrawalService.createAWithdrawal(accountId, withdrawalToBeCreated));
            logger.info("Withdrawal created successfully!");
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Cannot create a withdrawal! Account #" + accountId + " does not exist!");
            logger.info("Cannot create a withdrawal! Account #" + accountId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400,
                    "Error! Withdrawal cannot be created due to insufficient funds!");
            logger.info("Error! Withdrawal cannot be created due to insufficient funds!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400, "Error! Cannot create a withdrawal!");
            logger.info("Error! Cannot create a withdrawal!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //tested and works

    //HTTP method to update a specific existing withdrawal
    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Object> updateExistingWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawalToBeUpdated) {
        try {
            withdrawalService.updateExistingWithdrawal(withdrawalId, withdrawalToBeUpdated);
            CodeFactorWithoutData success = new CodeFactorWithoutData(202, "Withdrawal #" + withdrawalId + " updated successfully!");
            logger.info("Withdrawal #" + withdrawalId + " updated successfully!");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Withdrawal #" + withdrawalId + " does not exist!");
            logger.info("Error! Withdrawal #" + withdrawalId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400,
                    "Error! Withdrawal #" + withdrawalId + " couldn't be updated due to insufficient funds!");
            logger.info("Error! Cannot update withdrawal #" + withdrawalId + " due to insufficient funds!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //tested and works

    //HTTP method to delete an existing withdrawal
    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Object> deleteExistingWithdrawal(@PathVariable Long withdrawalId) {
        try {
            withdrawalService.deleteExistingWithdrawal(withdrawalId);
            logger.info("Successfully deleted withdrawal #" + withdrawalId + "!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Withdrawal #" + withdrawalId + " does not exist!");
            logger.info("Error! Cannot delete withdrawal #" + withdrawalId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

}
