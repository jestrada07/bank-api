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

    private Long recievingCustomer;

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

    public Long getRecievingCustomer() {
        return recievingCustomer;
    }

    public void setRecievingCustomer(Long recievingCustomer) {
        this.recievingCustomer = recievingCustomer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
