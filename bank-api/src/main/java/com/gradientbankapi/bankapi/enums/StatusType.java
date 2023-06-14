package com.gradientbankapi.bankapi.enums;

import com.gradientbankapi.bankapi.models.Deposit;
import com.gradientbankapi.bankapi.repos.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public enum StatusType {

    PENDING, CANCELLED, COMPLETED;

    @Autowired
    private DepositRepo depositRepo;

    public static StatusType getDefault(){
        return PENDING;
    }

}
