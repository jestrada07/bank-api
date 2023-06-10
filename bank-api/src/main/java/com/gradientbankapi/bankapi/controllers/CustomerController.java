package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    //HTTP method to retrieve a customer by their specified account
    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<Customer> getCustomerByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(customerService.getCustomerByAccountId(accountId), HttpStatus.OK);
    }

    //HTTP method to retrieve all customers
    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        logger.info("Successfully retrieved all customers");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    } //tested and works

    //HTTP method to get a customer by their ID
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Optional<Customer>> getACustomerById(@PathVariable Long customerId) {
        logger.info("Fetched customer ID #" + customerId);
        return new ResponseEntity<>(customerService.getACustomerById(customerId), HttpStatus.OK);
    } //tested and works

    //HTTP method to create a customer
    @PostMapping("/customers")
    public ResponseEntity<Void> createACustomer(@RequestBody Customer customerToBeCreated) {
        customerService.createACustomer(customerToBeCreated);
        logger.info("Successfully created a customer");
        return new ResponseEntity<>(HttpStatus.CREATED);
    } //tested and works

    //HTTP method to update a specific existing customer
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Void> updateExistingCustomer(@PathVariable Long customerId, @RequestBody Customer customerToBeUpdated) {
        customerService.updateExistingCustomer(customerId, customerToBeUpdated);
        logger.info("Successfully updated customer ID #" + customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    } //tested and works

    //HTTP method to delete a specific existing customer
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteExistingCustomer(@PathVariable Long customerId) {
        customerService.deleteExistingCustomer(customerId);
        logger.info("Successfully deleted customer ID #" + customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } //tested and works

}
