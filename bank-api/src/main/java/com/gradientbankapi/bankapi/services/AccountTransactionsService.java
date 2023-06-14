package com.gradientbankapi.bankapi.services;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.exceptions.ResourceNotFoundException;
import com.gradientbankapi.bankapi.models.*;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import com.gradientbankapi.bankapi.repos.TransferRepo;
import com.gradientbankapi.bankapi.repos.WithdrawalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTransactionsService {

    @Autowired
    private DepositRepo depositRepo;

    @Autowired
    private WithdrawalRepo withdrawalRepo;

    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    private AccountService accountService;

    public AccountTransactions getAccountTransactions(Long accountId) {
        List<Deposit> deposits = depositRepo.findAllDepositsByAccountId(accountId);
        List<Withdrawal> withdrawals = withdrawalRepo.findAllWithdrawalsByAccountId(accountId);
        List<Transfer> transfers = transferRepo.findAllBySendingCustomerOrReceivingCustomer(accountId,accountId);

        return new AccountTransactions(deposits, withdrawals, transfers);

    }

}