package com.bootcamp.withdrawservice.services.Impl;

import com.bootcamp.withdrawservice.documents.entities.Withdraw;
import com.bootcamp.withdrawservice.repositories.WithdrawRepository;
import com.bootcamp.withdrawservice.services.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WithdrawServiceImpl implements IWithdrawService {

    @Autowired
    WithdrawRepository repository;

    @Override
    public Mono<Withdraw> create(Withdraw o) {
        return repository.save(o);
    }

    @Override
    public Flux<Withdraw> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Withdraw> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Withdraw> update(Withdraw o) {
        return repository.save(o);
    }

    @Override
    public Mono<Void> delete(Withdraw o) {
        return repository.delete(o);
    }
}
