package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.Account;
import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.repos.AccountRepo;
import com.gradientbankapi.bankapi.repos.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private AccountRepo accountRepo;


    //post
    public void createBill(Long accountId, Bill bill) {
       Account account = accountRepo.findById(accountId).orElse(null);
       bill.setAccount(account);
        billRepo.save(bill);
    }

    //get bill by the bill id
    public Optional<Bill> showBillById(Long BillId) {
        verifyBill(BillId);
        return billRepo.findById(BillId);
    }

    //get all bills by customer id
    public List<Bill> showAllBillsForCustomer(Long customerId) {
        return billRepo.findAllByCustomer(customerId);
    }

    //get all bills by account id
    public Optional<Bill> showAllBillsForAccount(Long AccountId) {
        verifyBill(AccountId);
        return billRepo.findByAccount(AccountId);
    }

    //Updates bill
    public void updateBill(Long BillId, Bill bill){
        verifyBill(BillId);
        bill.setId(BillId);
        billRepo.save(bill);
    }

    //Deletes bill
    public void deleteBill(Long BillId){
        billRepo.deleteById(BillId);
    }

    protected void verifyBill(Long BillId) throws ResourceNotFoundException {
        Optional<Bill> Bill = billRepo.findById(BillId);
        if(Bill.isEmpty()){
            throw new ResourceNotFoundException("The Bill with id " + BillId + " does not exist");
        }
    }
}