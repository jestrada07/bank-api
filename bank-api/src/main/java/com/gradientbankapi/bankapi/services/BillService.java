package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.models.Customer;
import com.gradientbankapi.bankapi.models.Withdrawal;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private AccountRepo accountRepo;


    //Good
    //post
    @Transactional
    public void createBill(Long accountId, Bill bill) {
        // Find account or throw error if not found
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("The account with id " + accountId + " does not exist"));

        // Check if account has enough balance to pay the bill
        if (account.getBalance() < bill.getPayment_amount()) {
            throw new IllegalStateException("The account with id " + accountId + " has insufficient balance to pay this bill");
        }

        // Fetch the customer associated with the account
        Customer customer = account.getCustomer();

        // If the Customer is null, throw an exception or handle the case as required
        if (customer == null) {
            throw new IllegalStateException("The account with id " + accountId + " is not associated with any customer");
        }

        account.setBalance(account.getBalance() - bill.getPayment_amount()); // Decrease account balance by the bill amount
        accountRepo.save(account); // Save updated account to the database

        bill.setAccount(account);
        bill.setCustomer(customer);
        billRepo.save(bill);
    }



    //Good
    //get bill by the bill id
    public Optional<Bill> showBillById(Long BillId) {
        verifyBill(BillId);
        return billRepo.findById(BillId);
    }

    //get all bills by customer id
    public List<Bill> showAllBillsForCustomer(Long customerId) {
        return billRepo.findByAccount_CustomerId(customerId);
    }

    // get all bills by account id
    public List<Bill> showAllBillsForAccount(Long accountId) {
        return billRepo.findByAccount_Id(accountId);
    }

    //Good
    //Updates bill
    public void updateBill(Long billId, Bill updatedBill) {
        Bill originalBill = billRepo.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("A bill with ID " + billId + " does not exist"));

        Account account = originalBill.getAccount();

        if (updatedBill.getPayment_amount() != originalBill.getPayment_amount()) {
            // First, revert the original bill
            account.setBalance(account.getBalance() + originalBill.getPayment_amount());

            // Then, apply the updated bill
            if (account.getBalance() < updatedBill.getPayment_amount()) {
                throw new IllegalStateException("The account with ID " + account.getId() + " has insufficient balance for this bill payment");
            }

            account.setBalance(account.getBalance() - updatedBill.getPayment_amount());
            originalBill.setPayment_amount(updatedBill.getPayment_amount());
            accountRepo.save(account);
        }

        if (updatedBill.getStatus() != null) {
            originalBill.setStatus(updatedBill.getStatus());
        }
        if (updatedBill.getPayee() != null) {
            originalBill.setPayee(updatedBill.getPayee());
        }
        if (updatedBill.getNickname() != null) {
            originalBill.setNickname(updatedBill.getNickname());
        }
        if (updatedBill.getCreation_date() != null) {
            originalBill.setCreation_date(updatedBill.getCreation_date());
        }
        if (updatedBill.getPayment_date() != null) {
            originalBill.setPayment_date(updatedBill.getPayment_date());
        }
        if (updatedBill.getRecurring_date() != null) {
            originalBill.setRecurring_date(updatedBill.getRecurring_date());
        }
        if (updatedBill.getUpcoming_payment_date() != null) {
            originalBill.setUpcoming_payment_date(updatedBill.getUpcoming_payment_date());
        }
        if (updatedBill.getPayment_amount() != null) {
            originalBill.setPayment_amount(updatedBill.getPayment_amount());
        }

        billRepo.save(originalBill);
    }

    //Good
    //Deletes bill
    public void deleteBill(Long BillId){
        Bill beforeBillBalance = billRepo.findById(BillId)
                .orElseThrow(() -> new ResourceNotFoundException("A Bill with an ID of #" + BillId + " does not exist! :)"));
        Account account = beforeBillBalance.getAccount();
        //reverts back to its original balance
        account.setBalance(account.getBalance() + beforeBillBalance.getPayment_amount());
        billRepo.deleteById(BillId);
    }

    protected void verifyBill(Long BillId) throws ResourceNotFoundException {
        Optional<Bill> Bill = billRepo.findById(BillId);
        if(Bill.isEmpty()){
            throw new ResourceNotFoundException("The Bill with id " + BillId + " does not exist");
        }
    }
}