package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.services.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
    public ResponseEntity<Object> getBillThroughAccount(@PathVariable Long accountId){
//        BillLogs.info("Existing Bills for Account found");
        //return new ResponseEntity<>(billService.showAllBillsForAccount(accountId), HttpStatus.OK);
        List<Bill> bills = billService.showAllBillsForAccount(accountId);
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved all bills for account #" + accountId + "!", bills);
            BillLogs.info("Existing Bills for Account found");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch(Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error fetching bills");
            BillLogs.info("Bills cannot be retrieved");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works


    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<Object> getBillThroughCustomer(@PathVariable Long customerId){
//        BillLogs.info("Existing Bills for Customer found");
//        return new ResponseEntity<>(billService.showAllBillsForCustomer(customerId), HttpStatus.OK);
        List<Bill> bills = billService.showAllBillsForCustomer(customerId);
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved all bills for customer #" + customerId + "!", bills);
            BillLogs.info("Existing Bills for Customer found");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error fetching bills");
            BillLogs.info("Bills cannot be retrieved");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //works
    @GetMapping("/bills/{billId}")
    public ResponseEntity<Object> getBillThroughBillId(@PathVariable Long billId){
        BillLogs.info("Bill Id found");
        Optional<Bill> bills = billService.showBillById(billId);
        //return new ResponseEntity<>(billService.showBillById(billId), HttpStatus.OK);
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved bill #" + billId + "!", bills);
            BillLogs.info("The bill with id #" + billId + "has been retrieved!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch(Exception e){
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error fetching bill with id #" + billId);
            BillLogs.info("This bill does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //works
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<Object> createBillForAccount(@PathVariable Long accountId, @RequestBody Bill bill){
        BillLogs.info("Bill successfully constructed for account");
        billService.createBill(accountId, bill);
        //return new ResponseEntity<>(HttpStatus.CREATED);
        try {
            CodeMessageFactor success = new CodeMessageFactor(201, "Successfully created bill for account #" + accountId);
            BillLogs.info("The bill has been successfully created for account #" + accountId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch(Exception e){
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error creating bill: Account not found");
            BillLogs.info("Could not create bill");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //not working
    @PutMapping("/bills/{billId}")
    public ResponseEntity<Object> updatingBill(@PathVariable Long billId, @RequestBody Bill bill){
        BillLogs.info("Bill Id found");
        billService.updateBill(billId, bill);
        //return new ResponseEntity<>(HttpStatus.OK);
        try {
            CodeMessageFactor success = new CodeMessageFactor(202, "Accepted bill modification");
            BillLogs.info("Bill has been successfully modified");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e){
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Bill ID does not exist");
            BillLogs.info("Could not find bill with the provided id");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //working
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Object> deleteBill(@PathVariable Long billId){
        billService.deleteBill(billId);
        BillLogs.info("Bill deleted successfully");
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
            CodeMessageFactor success = new CodeMessageFactor(204, "Content successfully deleted");
            BillLogs.info("Bill has been successfully deleted");
            return new ResponseEntity<>(success, HttpStatus.NO_CONTENT);
        } catch (Exception e){
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "This id does not exist in bills");
            BillLogs.info("The bill with the provided id does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works
}
