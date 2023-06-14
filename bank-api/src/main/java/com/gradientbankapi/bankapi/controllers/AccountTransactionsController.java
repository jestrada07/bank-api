package com.gradientbankapi.bankapi.controllers;


import com.gradientbankapi.bankapi.models.AccountTransactions;
import com.gradientbankapi.bankapi.services.AccountService;
import com.gradientbankapi.bankapi.services.AccountTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountTransactionsController {

    @Autowired
    private AccountTransactionsService accountTransactionsService;

    @GetMapping("/api/account-transactions/{accountId}")
    public ResponseEntity<AccountTransactions> getAccountTransactions(@PathVariable Long accountId) {
        AccountTransactions accountTransactions = accountTransactionsService.getAccountTransactions(accountId);
        return ResponseEntity.ok(accountTransactions);
    }
}
