//package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.models.Account;
//import com.gradientbankapi.bankapi.models.AllTransactionActivity;
import com.gradientbankapi.bankapi.models.Customer;
//import com.gradientbankapi.bankapi.services.AllTransactionActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

//public class AllTransactionActivityController {
 //   @Autowired
  // private AllTransactionActivityService allTransactionActivityService;

//    @GetMapping("/transaction")
//    public ResponseEntity<?> getAllTransactions() {
//
//        Iterable<Account> accounts = allTransactionActivityService.getAllTransactions();
//
//        if (accounts.iterator().hasNext()) {
//            CodeMessageFactor successfullResponse = new CodeMessageFactor(200, "Success", accounts);
//            return new ResponseEntity<>(successfullResponse, HttpStatus.OK);
//        }
//
//        CodeFactorWithoutData failedResponse = new CodeFactorWithoutData(404, "Error fetching accounts");
//        return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
//
//
//    }

//}
