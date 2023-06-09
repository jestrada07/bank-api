package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    //find all accounts by the customer's ID
    List<Account> findAllAccountsByCustomerId(Long customerId);

}
