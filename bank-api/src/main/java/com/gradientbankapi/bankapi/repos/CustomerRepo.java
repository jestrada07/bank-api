package com.gradientbankapi.bankapi.repos;

import com.gradientbankapi.bankapi.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository <Customer, Long>  {



}
