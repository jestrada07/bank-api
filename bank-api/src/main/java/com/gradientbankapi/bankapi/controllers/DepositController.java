package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.services.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DepositController {

    private static final Logger logger = LoggerFactory.getLogger(DepositController.class);

    @Autowired
    DepositService depositService;

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<Void> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){
        depositService.createDeposit(deposit);
        logger.info("Deposit created Successfully!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/deposits/{depositId}")
    public Optional<Deposit> getDepositById(@PathVariable Long depositId){
        logger.info("Successfully retrieved deposit!");
        return depositService.getDepositById(depositId);
    }

    @GetMapping("/accounts/{accountId}/deposits")
    public Optional<Deposit> getDepositsByAccount(@PathVariable Long accountId){
        logger.info("Successfully retrieved deposits for this account!");
        return depositService.getDepositsForAccount(accountId);
    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<Void> updateBlog(@PathVariable Long depositId, @RequestBody Deposit deposit){
        depositService.updateDeposit(depositId, deposit);
        logger.info("Deposit updated successfully!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long depositId){
        depositService.deleteDeposit(depositId);
        logger.info("Deposit deleted successfully!");
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
<<<<<<< HEAD
=======



>>>>>>> master
}
