//package com.gradientbankapi.bankapi.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//
//@Entity
//public class AllTransactionActivity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Deposit deposit;
//    private Withdrawal withdrawal;
//
//    private Transfer transfer;
//
//
//    @JsonIgnore
//    private Customer customer;
//
//    public Deposit getDeposit() {
//        return deposit;
//    }
//
//    public void setDeposit(Deposit deposit) {
//        this.deposit = deposit;
//    }
//
//    public Withdrawal getWithdrawal() {
//        return withdrawal;
//    }
//
//    public void setWithdrawal(Withdrawal withdrawal) {
//        this.withdrawal = withdrawal;
//    }
//
//    public Transfer getTransfer() {
//        return transfer;
//    }
//
//    public void setTransfer(Transfer transfer) {
//        this.transfer = transfer;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//}
