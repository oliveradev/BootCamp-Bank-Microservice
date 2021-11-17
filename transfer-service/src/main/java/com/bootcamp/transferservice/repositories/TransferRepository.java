package com.bootcamp.transferservice.repositories;

import com.bootcamp.transferservice.documents.entities.Transfer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransferRepository extends ReactiveMongoRepository<Transfer, String> {
}
