package com.bootcamp.savingservice.services.Impl;

import com.bootcamp.savingservice.documents.entities.Account;
import com.bootcamp.savingservice.repositories.SavingRepository;
import com.bootcamp.savingservice.services.ISavingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingServiceImpl implements ISavingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SavingServiceImpl.class);

    @Autowired
    private SavingRepository repository;

    @Override
    public Mono<Account> create(Account o) {
        return repository.save(o);
    }

    @Override
    public Flux<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Account> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Account> update(Account o) {
        return repository.save(o);
    }

    @Override
    public Mono<Void> delete(Account o) {
        return repository.delete(o);
    }

    @Override
    public Mono<Account> findByCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findByCustomerIdentityNumber(customerIdentityNumber);
    }

    @Override
    public Mono<Account> validateCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findByCustomerIdentityNumber(customerIdentityNumber)
                .switchIfEmpty(Mono.just(Account.builder()
                        .customerIdentityNumber(null).build()));
    }

    @Override
    public Mono<Account> findByAccountNumber(String accountNumber) {
        LOGGER.info("Account Number: {}",accountNumber);
        return repository.findByAccountNumber(accountNumber);
    }
}
