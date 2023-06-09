package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.services.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    private static final Logger BillLogs = LoggerFactory.getLogger(BillController.class);

    @GetMapping("/accounts/{accountId}/bills")
    public Optional<Bill> getBillThroughAccount(@PathVariable Long accountId, @RequestBody Bill bill){
        BillLogs.info("Existing Bills for Account found");
        return billService.showAllBillsForAccount(accountId);
    }

    @GetMapping("/customers/{CustomerId}/bills")
    public Optional<Bill> getBillThroughCustomer(@PathVariable Long CustomerId, @RequestBody Bill bill){
        BillLogs.info("Existing Bills for Customer found");
        return billService.showAllBillsForCustomer(CustomerId);
    }

    @GetMapping("/bills/{billId}")
    public Optional<Bill> getBillThroughBillId(@PathVariable Long billId, @RequestBody Bill bill){
        BillLogs.info("Bill Id found");
        return billService.showBillById(billId);
    }

//    @PostMapping("/accounts/{accountId}/bills")
//    public ResponseEntity<Void> createBillForAccount(@PathVariable Long accountId, @RequestBody Bill bill){
//        BillLogs.info("Bill successfully constructed for account");
//        billService.createBill(bill);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long billId){
        billService.deleteBill(billId);
        BillLogs.info("Bill deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
