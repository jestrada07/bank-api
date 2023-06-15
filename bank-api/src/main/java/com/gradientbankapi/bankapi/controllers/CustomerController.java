package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.models.Address;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    //HTTP method to retrieve a customer by their specified account
    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId) {
        Customer customer = customerService.getCustomerByAccountId(accountId);
        if (customer == null) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Could not find customer; account #" + accountId + " does not exist!");
            logger.info("Error! Could not find customer; account #" + accountId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageFactor success = new CodeMessageFactor(200,
                "Successfully retrieved customer from account #" + accountId + "!", customer);
        logger.info("Successfully retrieved customer from account #" + accountId + "!");
        return new ResponseEntity<>(success, HttpStatus.OK);
    } //tested and works

    //HTTP method to retrieve all customers
    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers() {
        Iterable<Customer> customers = customerService.getAllCustomers();
        if (customers.iterator().hasNext()) {
            CodeMessageFactor success = new CodeMessageFactor(200,
                    "Successfully retrieved all customers!", customers);
            logger.info("Successfully retrieved all customers!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                "Error fetching customers! Customers were not created!");
        logger.info("Error fetching customers! Customers were not created!");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    } //tested and works

    //HTTP method to get a customer by their ID
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Object> getACustomerById(@PathVariable Long customerId) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved customer #" + customerId,
                    customerService.getACustomerById(customerId));
            logger.info("Successfully retrieved customer #" + customerId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Customer #" + customerId + " does not exist!");
            logger.info("Error! Customer #" + customerId + " does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //HTTP method to create a customer
    @PostMapping("/customers")
    public ResponseEntity<Object> createACustomer(@RequestBody Customer customerToBeCreated) {
        try {
            Set<Address> addresses = customerToBeCreated.getAddress();
            if (addresses != null) {
                for (Address address : addresses) {
                    address.setCustomer(customerToBeCreated);
                }
            }
            customerService.createACustomer(customerToBeCreated);
            CodeMessageFactor success = new CodeMessageFactor(201, "Customer account created", customerService.createACustomer(customerToBeCreated));
            logger.info("Successfully created a customer!");
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            CodeFactorWithoutData error = new CodeFactorWithoutData(400, "Error creating customer");
            logger.info("Error! Cannot create customer!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    } //tested and works

    //HTTP method to update a specific existing customer
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Object> updateExistingCustomer(@PathVariable Long customerId, @RequestBody Customer customerToBeUpdated) {
        try { customerService.updateExistingCustomer(customerId, customerToBeUpdated);
            CodeFactorWithoutData success = new CodeFactorWithoutData(202, "Customer #" + customerId + " updated successfully!");
            logger.info("Customer #" + customerId + " updated successfully!");
            return new ResponseEntity<>(success, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Customer #" + customerId + " does not exist!");
            logger.info("Error! Customer #" + customerId + " does not exist!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    //HTTP method to delete a specific existing customer
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Object> deleteExistingCustomer(@PathVariable Long customerId) {
        try {
            customerService.deleteExistingCustomer(customerId);
            logger.info("Successfully deleted customer #" + customerId + "!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Customer #" + customerId + " does not exist!");
            logger.info("Error! Cannot delete customer #" + customerId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works

    @GetMapping("/customer")  //search functionality that searches a customer by its name
    public ResponseEntity<Object> getAllOrGetCustomerByName(@RequestParam(value = "name")  String FirstName) {
        CodeMessageFactor success = new CodeMessageFactor(200, "Customer name '" + FirstName + "' successfully retrieved!",
                customerService.getCustomerByName(FirstName));
        logger.info("Customer name '" + FirstName + "' successfully retrieved!");
        return (new ResponseEntity<>(success, HttpStatus.OK));
    } // tested and works

}
