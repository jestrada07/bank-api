package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    //HTTP method to retrieve a customer by their specified account
    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId) {
        Customer customer = customerService.getCustomerByAccountId(accountId).orElse(null);
        if (customer == null) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404,
                    "Error! Couldn't find customer with an account ID of #" + accountId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved customer", customer);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    //This method above is giving me some problems. Everytime I tried to get the account ID for the customer,
    //it's still giving me the error response. Most other methods in this class work well.

    //HTTP method to retrieve all customers
    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers() {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved all customers", customerService.getAllCustomers());
            logger.info("Successfully retrieved all customers!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error fetching customers!");
            logger.info("Error! Cannot fetch customers!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

 //tested and works


    //HTTP method to get a customer by their ID
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Object> getACustomerById(@PathVariable Long customerId) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Successfully retrieved customer #" + customerId, customerService.getACustomerById(customerId));
            logger.info("Successfully retrieved customer #" + customerId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Customer #" + customerId + " does not exist!");
            logger.info("Error! Customer #" + customerId + " does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        //tested and works
    }

    //HTTP method to create a customer
    @PostMapping("/customers")
    public ResponseEntity<Object> createACustomer(@RequestBody Customer customerToBeCreated) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(201, "Customer account created", customerService.createACustomer(customerToBeCreated));
            logger.info("Successfully created a customer!");
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400, "Error creating customer");
            logger.info("Error! Cannot create customer!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

     //tested and works except no address


    //HTTP method to update a specific existing customer
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Object> updateExistingCustomer(@PathVariable Long customerId, @RequestBody Customer customerToBeUpdated) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(200, "Customer #" + customerId + " updated successfully!",
                    customerService.updateExistingCustomer(customerId, customerToBeUpdated));
            logger.info("Customer #" + customerId + " updated successfully!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Customer #" + customerId + " does not exist!");
            logger.info("Error! Cannot update customer #" + customerId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

     //tested and works without address


    //HTTP method to delete a specific existing customer
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Object> deleteExistingCustomer(@PathVariable Long customerId) {
        try {
            CodeFactorWithoutData success = new CodeFactorWithoutData(204, "Customer #" + customerId + " successfully deleted!");
            customerService.deleteExistingCustomer(customerId);
            logger.info("Successfully deleted customer #" + customerId + "!");
            return new ResponseEntity<>(success, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(404, "Error! Customer #" + customerId + " does not exist!");
            logger.info("Error! Cannot delete customer #" + customerId);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    } //tested and works
    //Couldn't figure out how to get the successful delete message to respond but, I don't think
    //it's necessary since it's not in the book. Everything here works fine except for the top method.

    @GetMapping("/customer")  //search functionality that searches a customer by its name
    public ResponseEntity<Object> getAllOrGetAccountByNickName(@RequestParam(value = "name")  String FirstName) {
        return (new ResponseEntity<>(this.customerService.getCustomerByName(FirstName), HttpStatus.OK));

    } // tested and works





}
