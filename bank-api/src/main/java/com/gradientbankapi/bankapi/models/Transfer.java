package com.gradientbankapi.bankapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sendingCustomer;

    private Long receivingCustomer;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
