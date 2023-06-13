package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.services.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {

    private static final Logger logger = LoggerFactory.getLogger(DepositController.class);

    @Autowired
    DepositService depositService;

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<Object> createDeposit(@PathVariable Long accountId, @RequestBody Deposit depositToBeCreated) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(201, "Deposit created successfully!",
                    depositService.createDeposit(accountId, depositToBeCreated));
            logger.info("Deposit created successfully!");
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Deposit couldn't be created! Account #" + accountId + " does not exist!");
            logger.info("Deposit couldn't be created! Account #" + accountId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400, "Error creating deposit!");
            logger.info("Error! Cannot create a deposit!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //This is good

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<Object> getDepositById(@PathVariable Long depositId) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved deposit type #" + depositId,
                    depositService.getDepositById(depositId));
            logger.info("Successfully retrieved deposit type #" + depositId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Deposit type #" + depositId + " does not exist!");
            logger.info("Error! Deposit type #" + depositId + " does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //This is good

    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<Object> getDepositsByAccount(@PathVariable Long accountId) {
        List<Deposit> deposits = depositService.getDepositsForAccount(accountId);
        if(deposits.isEmpty()) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Cannot retrieve all deposits! Account #" + accountId + " does not exist!");
            logger.info("Error! Cannot retrieve all deposits! Account #" + accountId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageFactor success = new CodeMessageFactor(200,
                "Successfully retrieved all deposits from account #" + accountId, deposits);
        logger.info("Successfully retrieved all deposits from account #" + accountId);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }// This is good

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<Object> updateDeposit (@PathVariable Long depositId, @RequestBody Deposit deposit) {
        try {
            depositService.updateDeposit(depositId, deposit);
            CodeFactorWithoutData success = new CodeFactorWithoutData(202, "Deposit type #" + depositId + " updated successfully!");
            logger.info("Deposit type #" + depositId + " updated successfully!");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Deposit type #" + depositId + " does not exist!");
            logger.info("Error! Deposit type #" + depositId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400,
                    "Error! Deposit type #" + depositId + " couldn't be updated!");
            logger.info("Error! Cannot update deposit type #" + depositId + "!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //This is good

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<Object> deleteDeposit (@PathVariable Long depositId){
        try {
            depositService.deleteDeposit(depositId);
            logger.info("Successfully deleted deposit type #" + depositId + "!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Deposit type #" + depositId + " does not exist!");
            logger.info("Error! Cannot delete deposit type #" + depositId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //This is good

}
