package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.gradientbankapi.bankapi.models.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepo extends CrudRepository<Transfer, Long> {
    List<Transfer> findAllBySendingCustomerOrReceivingCustomer(Long sendingCustomer, Long recievingCustomer);
}


