package com.gradientbankapi.bankapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gradientbankapi.bankapi.enums.TransactionType;
import com.gradientbankapi.bankapi.enums.MediumType;
import com.gradientbankapi.bankapi.enums.StatusType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.gradientbankapi.bankapi.enums.StatusType.COMPLETED;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private TransactionType type;
    private String transaction_date;

    @PrePersist
    protected void onCreate() {
        transaction_date = LocalDate.now().toString();
    }
    private StatusType status = StatusType.getDefault();


    @NotNull
    private Long payee_id;
    @NotNull
    private MediumType medium;
    @NotNull
    @Min(1)
    private double amount;
    @NotEmpty
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
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
