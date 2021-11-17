package com.bootcamp.savingservice.repositories;

import com.bootcamp.savingservice.documents.entities.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SavingRepository extends ReactiveMongoRepository<Account, String> {

    Mono<Account> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<Account> findByAccountNumber(String accountNumber);

}
