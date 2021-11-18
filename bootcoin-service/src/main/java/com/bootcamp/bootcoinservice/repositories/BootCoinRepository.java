package com.bootcamp.bootcoinservice.repositories;

import com.bootcamp.bootcoinservice.documents.entities.BootCoin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BootCoinRepository extends ReactiveMongoRepository<BootCoin, String> {
    Mono<BootCoin> findByPhoneNumber(String customerIdentityNumber);
}
