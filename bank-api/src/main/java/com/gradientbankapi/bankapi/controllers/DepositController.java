package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.services.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Fetched deposits from account #" + accountId + "!",
                    depositService.getDepositsForAccount(accountId));
            logger.info("Successfully fetched deposits from account #" + accountId + "!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400,
                    "Error fetching deposits from account #" + accountId + "!");
            logger.info("Error! Cannot fetched deposits from account #" + accountId + "!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }// This is good


    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<Object> updateDeposit (@PathVariable Long depositId, @RequestBody Deposit deposit) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Deposit type #" + depositId + " updated successfully!",
                    depositService.updateDeposit(depositId, deposit));
            logger.info("Deposit type #" + depositId + " updated successfully!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Deposit type #" + depositId + " does not exist!");
            logger.info("Error! Cannot update deposit type #" + depositId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //This is good

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<Object> deleteDeposit (@PathVariable Long depositId){
        try {
            CodeFactorWithoutData success = new CodeFactorWithoutData(200, "Deposit type #" + depositId + " successfully deleted!");
            depositService.deleteDeposit(depositId);
            logger.info("Successfully deleted deposit type #" + depositId + "!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Deposit type #" + depositId + " does not exist!");
            logger.info("Error! Cannot delete deposit type #" + depositId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //This is good

}
