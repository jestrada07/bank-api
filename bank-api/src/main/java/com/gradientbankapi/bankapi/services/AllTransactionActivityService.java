//package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.models.*;
import com.gradientbankapi.bankapi.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class AllTransactionActivityService {
//    @Autowired
//    TransferRepo transferRepo;
//    @Autowired
//    DepositRepo depositRepo;
//    @Autowired
//    WithdrawalRepo withdrawalRepo;
//
//    @Autowired
//    AccountRepo accountRepo;
//
//    @Autowired
//    AllTransactionActivityRepo allTransactionActivityRepo;
//   public List<AllTransactionActivity> getAllTransactions(){
//       List<AllTransactionActivity> allTransactions = new ArrayList<>();
//
//       Iterable<Transfer> transfers = transferRepo.findAll();
//       allTransactions.addAll(transfers);
//
//       // Retrieve all deposits
//       Iterable<Deposit> deposits = depositRepo.findAll();
//       allTransactions.addAll(deposits);
//
//       // Retrieve all withdrawals
//       Iterable<Withdrawal> withdrawals = withdrawalRepo.findAll();
//       allTransactions.addAll(withdrawals);
//
//       return allTransactions;
//
//
//
//
//   }





