package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {


}
