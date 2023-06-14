package com.gradientbankapi.bankapi.models;

import com.gradientbankapi.bankapi.enums.StatusType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sendingCustomer;

    private Long receivingCustomer;

    private String transaction_date;
    @PrePersist
    protected void onCreate() {
        transaction_date = LocalDate.now().toString(); // or any other logic to set the date
    }

    private StatusType status = StatusType.getDefault();

    private double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendingCustomer() {
        return sendingCustomer;
    }

    public void setSendingCustomer(Long sendingCustomer) {
        this.sendingCustomer = sendingCustomer;
    }

    public Long getReceivingCustomer() {
        return receivingCustomer;
    }

    public void setReceivingCustomer(Long receivingCustomer) {
        this.receivingCustomer = receivingCustomer;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
