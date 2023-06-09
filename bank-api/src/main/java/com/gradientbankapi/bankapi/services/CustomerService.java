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




    public void createCustomer(Customer customer){
        customerRepo.save(customer);

    }
    public Iterable<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public void updateCustomer(Long id, Customer customer){
        customer.setId(id);
        customerRepo.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

    public Optional<Customer> findACustomerByCustomerId(Long customerId){

        return customerRepo.findById(customerId);
    }

    public Optional<Account> findACustomerByAccountId(Long accountId){
       return accountRepo.findById(accountId);
    }

    protected void checkIfCustomerExists(Long idOfCustomer) throws ResourceNotFoundException {
        if (!(this.customerRepo.existsById(idOfCustomer))) {
            throw (new ResourceNotFoundException("Customer ID " + idOfCustomer + " not found"));
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
