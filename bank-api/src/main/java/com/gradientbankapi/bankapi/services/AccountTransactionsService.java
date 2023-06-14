package com.gradientbankapi.bankapi.services;
import com.gradientbankapi.bankapi.models.*;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import com.gradientbankapi.bankapi.repos.TransferRepo;
import com.gradientbankapi.bankapi.repos.WithdrawalRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionsService {

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionsService.class);

    @Autowired
    private DepositRepo depositRepo;

    @Autowired
    private WithdrawalRepo withdrawalRepo;

    @Autowired
    private TransferRepo transferRepo;

    public AccountTransactions getAccountTransactions(Long accountId) {
        List<Deposit> deposits = depositRepo.findAllDepositsByAccountId(accountId);
        List<Withdrawal> withdrawals = withdrawalRepo.findAllWithdrawalsByAccountId(accountId);
        List<Transfer> transfers = transferRepo.findAllBySendingCustomerOrReceivingCustomer(accountId,accountId);

        return new AccountTransactions(deposits, withdrawals, transfers);
    }

}