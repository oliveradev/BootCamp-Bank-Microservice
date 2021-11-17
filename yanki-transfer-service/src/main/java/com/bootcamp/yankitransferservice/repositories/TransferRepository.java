package com.bootcamp.yankitransferservice.repositories;

import com.bootcamp.yankitransferservice.documents.entities.Transfer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface TransferRepository extends ReactiveMongoRepository<Transfer, String> {
    Mono<Transfer> findByMainPhoneNumber(String phoneNumber);
}
