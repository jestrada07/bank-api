package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.services.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    private static final Logger BillLogs = LoggerFactory.getLogger(BillController.class);

    //not working
    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<List<Bill>> getBillThroughAccount(@PathVariable Long accountId){
        BillLogs.info("Existing Bills for Account found");
        return new ResponseEntity<>(billService.showAllBillsForAccount(accountId), HttpStatus.OK);
    }

    //not working
    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<List<Bill>> getBillThroughCustomer(@PathVariable Long customerId){
        BillLogs.info("Existing Bills for Customer found");
        return new ResponseEntity<>(billService.showAllBillsForCustomer(customerId), HttpStatus.OK);
    }

    //works
    @GetMapping("/bills/{billId}")
    public ResponseEntity<Optional<Bill>> getBillThroughBillId(@PathVariable Long billId){
        BillLogs.info("Bill Id found");
        return new ResponseEntity<>(billService.showBillById(billId), HttpStatus.OK);
    }

    //works
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<Void> createBillForAccount(@PathVariable Long accountId, @RequestBody Bill bill){
        BillLogs.info("Bill successfully constructed for account");
        billService.createBill(accountId, bill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //not working
    @PutMapping("/bills/{billId}")
    public ResponseEntity<Void> updatingBill(@PathVariable Long billId, @RequestBody Bill bill){
        BillLogs.info("Bill Id found");
        billService.updateBill(billId, bill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //working
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long billId){
        billService.deleteBill(billId);
        BillLogs.info("Bill deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
