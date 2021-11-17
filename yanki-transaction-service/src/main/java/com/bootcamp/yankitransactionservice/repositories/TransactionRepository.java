package com.bootcamp.yankitransactionservice.repositories;

import com.bootcamp.yankitransactionservice.documents.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
}
