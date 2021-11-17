package com.bootcamp.yankitransactionservice.services.Impl;

import com.bootcamp.yankitransactionservice.documents.Transaction;
import com.bootcamp.yankitransactionservice.repositories.TransactionRepository;
import com.bootcamp.yankitransactionservice.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public Mono<Transaction> create(Transaction o) {
        return repository.save(o);
    }

    @Override
    public Mono<Transaction> update(Transaction o) {
        return repository.save(o);
    }

    @Override
    public Mono<Void> delete(Transaction o) {
        return repository.delete(o);
    }

    @Override
    public Flux<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Transaction> findById(String s) {
        return repository.findById(s);
    }
}
