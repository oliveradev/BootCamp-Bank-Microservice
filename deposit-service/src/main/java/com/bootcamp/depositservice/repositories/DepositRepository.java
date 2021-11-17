package com.bootcamp.depositservice.repositories;

import com.bootcamp.depositservice.models.Deposit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends ReactiveMongoRepository<Deposit, String>  {
}
