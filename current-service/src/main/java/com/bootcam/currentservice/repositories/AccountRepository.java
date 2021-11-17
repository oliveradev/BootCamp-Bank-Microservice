package com.bootcam.currentservice.repositories;

import com.bootcam.currentservice.models.entities.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Account repository.
 */
public interface AccountRepository extends ReactiveMongoRepository<Account,String> {
    /**
     * Find by customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    Flux<Account> findAllByCustomerIdentityNumber(String customerIdentityNumber);


    Mono<Account> findByCustomerIdentityNumber(String customerIdentityNumber);
    /**
     * Find by account number mono.
     *
     * @param accountNumber the account number
     * @return the mono
     */
    public Mono<Account> findByAccountNumber(String accountNumber);
}
