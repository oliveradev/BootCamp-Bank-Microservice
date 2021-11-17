package com.bootcamp.transactionservice.repositories;

import com.bootcamp.transactionservice.models.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    /**
     * Find all by identity number flux.
     *
     * @param identityNumber the identity number
     * @return the flux
     */
    Flux<Transaction> findAllByIdentityNumber(String identityNumber);

    Flux<Transaction> findFirst10ByIdentityNumberOrderByDateOperationDesc(String identityNumber);

    Flux<Transaction> findAllByIdentityNumberAndDateOperationBetween(String identityNumber
            , LocalDateTime fromDate, LocalDateTime toDate);
}
