package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepo extends CrudRepository<Bill, Long> {

    Optional<Bill> findByAccount(Long accountId);

    Optional<Bill> findByCustomer(Long CustomerId);

}
