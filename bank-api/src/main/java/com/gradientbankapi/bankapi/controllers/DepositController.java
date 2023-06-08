package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {

    @Autowired
    DepositService depositService;

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<Void> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){
        depositService.createDeposit(deposit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<Void> updateBlog(@PathVariable Long depositId, @RequestBody Deposit deposit){
        depositService.updateDeposit(depositId, deposit);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
