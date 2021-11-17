package com.bootcam.currentservice.services.Impl;

import com.bootcam.currentservice.models.entities.Account;
import com.bootcam.currentservice.repositories.AccountRepository;
import com.bootcam.currentservice.services.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements IAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository repository;

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
    public Mono<Account> validateCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findByCustomerIdentityNumber(customerIdentityNumber)
                .switchIfEmpty(Mono.just(Account.builder()
                        .customerIdentityNumber(null).build()));
    }

    @Override
    public Flux<Account> findAllByCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findAllByCustomerIdentityNumber(customerIdentityNumber);
    }

    @Override
    public Mono<Account> findByAccountNumber(String accountNumber) {
        LOGGER.info("El AccountNumber es" + accountNumber);
        return repository.findByAccountNumber(accountNumber);
    }

    @Override
    public Mono<Account> findByCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findByCustomerIdentityNumber(customerIdentityNumber);
    }
}
