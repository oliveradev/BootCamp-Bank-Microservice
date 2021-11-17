package com.bootcamp.creditservice.repositories;

import com.bootcamp.creditservice.models.entities.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {

    Flux<Credit> findAllByCustomerIdentityNumber(String customerIdentityNumber);
    Mono<Credit> findByCustomerIdentityNumber(String customerIdentityNumber);
    Mono<Credit> findByContractNumber(String contractNumber);
}
