package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalRepo extends CrudRepository<Withdrawal, Long> {

    //find all withdrawals by the account's id
    List<Withdrawal> findAllWithdrawalsByAccountId(Long accountId);

}
