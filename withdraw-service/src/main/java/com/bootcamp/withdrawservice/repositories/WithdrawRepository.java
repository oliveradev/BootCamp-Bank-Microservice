package com.bootcamp.withdrawservice.repositories;

import com.bootcamp.withdrawservice.documents.entities.Withdraw;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface WithdrawRepository extends ReactiveMongoRepository<Withdraw, String> {
}
