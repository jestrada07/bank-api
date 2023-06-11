package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
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
        List<Bill> bills = billService.showAllBillsForAccount(accountId);
        if(bills.isEmpty()) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Cannot retrieve all bills; Account #" + accountId + " does not exist!");
            BillLogs.info("Error! Cannot retrieve all deposits; Account #" + accountId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageFactor success = new CodeMessageFactor(200,
                "Successfully retrieved all deposits from account #" + accountId, bills);
        BillLogs.info("Successfully retrieved all deposits from account #" + accountId);
        return new ResponseEntity<>(success, HttpStatus.OK);
    } //tested and works


    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<Object> getBillThroughCustomer(@PathVariable Long customerId){
        List<Bill> bills = billService.showAllBillsForCustomer(customerId);
        if(bills.isEmpty()) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Cannot retrieve all bills; Customer #" + customerId + " does not exist!");
            BillLogs.info("Error! Cannot retrieve all bills; Customer #" + customerId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageFactor success = new CodeMessageFactor(200,
                "Successfully retrieved all bills from customer #" + customerId, bills);
        BillLogs.info("Successfully retrieved all bills from account #" + customerId);
        return new ResponseEntity<>(success, HttpStatus.OK);
    } //tested and works

    //works
    @GetMapping("/bills/{billId}")
    public ResponseEntity<Object> getBillThroughBillId(@PathVariable Long billId){
        Optional<Bill> bills = billService.showBillById(billId);
        if(bills.isEmpty()) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Cannot retrieve a bill; Bill #" + billId + " does not exist!");
            BillLogs.info("Error! Cannot retrieve a bill; Bill #" + billId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageFactor success = new CodeMessageFactor(200,
                "Successfully retrieved bill #" + billId + "!", bills);
        BillLogs.info("Successfully retrieved bill #" + billId + "!");
        return new ResponseEntity<>(success, HttpStatus.OK);
    } //tested and works

    //works
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<Object> createBillForAccount(@PathVariable Long accountId, @RequestBody Bill bill){
        try {
            CodeMessageFactor success = new CodeMessageFactor(201, "Successfully created bill for account #" + accountId,
                    billService.createBill(accountId, bill));
            BillLogs.info("The bill has been successfully created for account #" + accountId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error creating bill: Account not found");
            BillLogs.info("Could not create bill");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400,
                    "The account with id " + accountId + " has insufficient balance to pay this bill");
            BillLogs.info("Could not create bill due to insufficient funds");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //tested and works

    //not working
    @PutMapping("/bills/{billId}")
    public ResponseEntity<Object> updatingBill(@PathVariable Long billId, @RequestBody Bill bill){
        try {
            CodeMessageFactor success = new CodeMessageFactor(202, "Accepted bill modification",
                    billService.updateBill(billId, bill));
            BillLogs.info("Bill has been successfully modified");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (Exception e){
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Bill ID does not exist");
            BillLogs.info("Could not find bill with the provided ID");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            CodeFactorWithoutData error = new CodeFactorWithoutData(400,
                    "Bill cannot be updated due to insufficient funds");
            BillLogs.info("Bill cannot be updated due to insufficient funds");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //tested and works

    //working
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Object> deleteBill(@PathVariable Long billId){
        try {
            CodeFactorWithoutData success = new CodeFactorWithoutData(200, "Bill #" + billId + " successfully deleted!");
            billService.deleteBill(billId);
            BillLogs.info("Successfully deleted bill #" + billId + "!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Bill #" + billId + " does not exist!");
            BillLogs.info("Error! Cannot delete bill #" + billId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works
}
