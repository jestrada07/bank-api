package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Bill;
import com.gradientbankapi.bankapi.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BillRepo extends CrudRepository<Bill, Long> {
    List<Bill> findByAccount_CustomerId(Long customerId); // Updated method signature

    List<Bill> findByAccount_Id(Long accountId); // Updated method signature
}

