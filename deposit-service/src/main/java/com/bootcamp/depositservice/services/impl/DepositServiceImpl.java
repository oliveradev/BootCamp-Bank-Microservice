package com.bootcamp.depositservice.services.impl;

import com.bootcamp.depositservice.models.Deposit;
import com.bootcamp.depositservice.repositories.DepositRepository;
import com.bootcamp.depositservice.services.IDepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepositServiceImpl implements IDepositService {

    private static final Logger log = LoggerFactory.getLogger(DepositServiceImpl.class);

    @Autowired
    private DepositRepository repository;


    @Override
    public Mono<Deposit> create(Deposit o) {
        return repository.save(o);
    }

    @Override
    public Flux<Deposit> findAll() {
        log.info("Retornado todos");
        return repository.findAll();
    }

    @Override
    public Mono<Deposit> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Deposit> update(Deposit o) {
        return repository.save(o);
    }

    @Override
    public Mono<Void> delete(Deposit o) {
        return repository.delete(o);
    }
}
