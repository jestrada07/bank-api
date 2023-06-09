package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.repos.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;


    public void createBill(Bill bill) {
        billRepo.save(bill);
    }

    public void showBillById(Long BillId) {
        billRepo.findAllById(BillId);
    }
}