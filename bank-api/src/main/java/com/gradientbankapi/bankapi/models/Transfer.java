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

    //sets default value for the transaction date - executes before entity is created
    @PrePersist //By using @PrePersist and the onCreate() method, the code ensures that the transaction_date attribute is automatically populated with the current date before the entity is saved to the database. This can be useful for maintaining a record of when each entity was created or when a specific transaction occurred.
    protected void onCreate() {
        transaction_date = LocalDate.now().toString();
    } // It retrieves the current date and converts it to a string representation, which is then assigned to the transaction_date variable.

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
