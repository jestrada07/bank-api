package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.NoSuchPropertyException;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountRepo accountRepo;

    //get a customer that owns the specified account
    public Customer getCustomerByAccountId(Long accountId) {
        return customerRepo.findById(accountId).orElse(null);
    }

    //get all customers
    public Iterable<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    //get a customer by their ID
    public Optional<Customer> getACustomerById(Long customerId) {
        verifyCustomer(customerId);
        return customerRepo.findById(customerId);
    }

    //create a customer
    public void createACustomer(Customer customerToBeCreated) {
        customerRepo.save(customerToBeCreated);
    }

    //update a specific existing customer
    public void updateExistingCustomer(Long customerId, Customer customerToBeUpdated) {
        verifyCustomer(customerId);
        customerToBeUpdated.setId(customerId);
        customerRepo.save(customerToBeUpdated);
    }

    //delete a specific existing customer
    public void deleteExistingCustomer(Long customerId) {
        verifyCustomer(customerId);
        customerRepo.deleteById(customerId);
    }

    protected void verifyCustomer(Long customerId) throws ResourceNotFoundException {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isEmpty()) {
            throw new ResourceNotFoundException("A customer with an ID of #" + customerId + " does not exist! :O");
        }
    }

    public Customer getCustomerByName(String FirstName, String LastName) throws NoSuchPropertyException, ResourceNotFoundException {
        boolean isEmptyString = FirstName.isEmpty() && LastName.isEmpty();
        if (isEmptyString) {
            throw (new NoSuchPropertyException("No name was provided"));
        } else {
            for (Customer customer : this.customerRepo.findAll()) {
                if (customer.getFirst_Name().equalsIgnoreCase(FirstName)&& customer.getLast_Name().equalsIgnoreCase(LastName)) {
                    return customer;
                }
            }
        }
        throw (new ResourceNotFoundException("Customer with name " + FirstName + " " +  LastName + " not found"));
    }

}
