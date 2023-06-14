package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BillRepo extends CrudRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE b.account.customer.id = :customerId")
    Iterable<Bill> findByAccount_CustomerId(Long customerId);


    @Query("SELECT b FROM Bill b WHERE b.account.id = :accountId")
    Iterable<Bill> findByAccount_Id(Long accountId);


}

