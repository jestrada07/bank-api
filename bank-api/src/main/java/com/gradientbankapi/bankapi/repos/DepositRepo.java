package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



import java.util.Optional;


@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {



    List<Deposit> findAllDepositsByAccountId(Long accountId);


}
