//package com.gradientbankapi.bankapi.services;
//
//import com.gradientbankapi.bankapi.controllers.AccountTransactionsController;
//import com.gradientbankapi.bankapi.enums.StatusType;
//import com.gradientbankapi.bankapi.models.Deposit;
//import com.gradientbankapi.bankapi.models.Transfer;
//import com.gradientbankapi.bankapi.models.Withdrawal;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import static com.gradientbankapi.bankapi.enums.StatusType.COMPLETED;
//
//@Component
//public class time {
//
//    private static final Logger logger = LoggerFactory.getLogger(time.class);
//
//    @Scheduled(fixedRate = 5000)
//    public void pendingTimeDeposit(StatusType Status) {
//        Deposit d = new Deposit();
//        d.setStatus(COMPLETED);
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void pendingTimeWithdrawal(StatusType Status) {
//        Withdrawal w = new Withdrawal();
//        w.setStatus(COMPLETED);
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void pendingTimeTransfer(StatusType Status) {
//        Transfer t = new Transfer();
//        t.setStatus(COMPLETED);
//    }
//
//}
