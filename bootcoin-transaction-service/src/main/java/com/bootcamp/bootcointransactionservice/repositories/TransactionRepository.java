package com.bootcamp.bootcointransactionservice.repositories;

import com.bootcamp.bootcointransactionservice.documents.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
}
