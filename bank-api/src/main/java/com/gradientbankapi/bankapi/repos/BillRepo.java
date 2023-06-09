package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepo extends CrudRepository<Bill, Long> {
}
