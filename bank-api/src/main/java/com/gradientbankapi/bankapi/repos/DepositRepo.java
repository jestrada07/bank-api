package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {

    Optional<Deposit> findByAccount(Long accountId);

}
