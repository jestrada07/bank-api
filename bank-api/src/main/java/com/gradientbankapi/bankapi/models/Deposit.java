package com.gradientbankapi.bankapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gradientbankapi.bankapi.enums.TransactionType;
import com.gradientbankapi.bankapi.enums.MediumType;
import com.gradientbankapi.bankapi.enums.StatusType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private TransactionType type;
    private String transaction_date;
    private StatusType status;
    @NotEmpty
    private Long payee_id;
    @NotEmpty
    private MediumType medium;
    @NotEmpty
    private double amount;
    @NotEmpty
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public MediumType getMedium() {
        return medium;
    }

    public void setMedium(MediumType medium) {
        this.medium = medium;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
