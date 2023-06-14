package com.gradientbankapi.bankapi.models;

import java.util.List;

public class AccountTransactions {
    private List<Deposit> deposits;
    private List<Withdrawal> withdrawals;
    private List<Transfer> transfers;

    public AccountTransactions(List<Deposit> deposits, List<Withdrawal> withdrawals, List<Transfer> transfers) {
        this.deposits = deposits;
        this.withdrawals = withdrawals;
        this.transfers = transfers;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public List<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }
}
