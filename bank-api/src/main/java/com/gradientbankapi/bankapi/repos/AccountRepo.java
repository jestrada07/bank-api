package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {



}
