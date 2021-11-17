package com.bootcamp.yankiservice.services.Impl;

import com.bootcamp.yankiservice.documents.YankiAccount;
import com.bootcamp.yankiservice.repositories.YankiRepository;
import com.bootcamp.yankiservice.services.IYankiService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class YankiServiceImpl implements IYankiService {



    @Autowired
    private YankiRepository repository;

    @Override
    public Mono<YankiAccount> create(YankiAccount o) {
        return repository.save(o);
    }

    @Override
    public Mono<YankiAccount> update(YankiAccount o) {
        return repository.save(o);
    }

    @Override
    public Mono<Void> delete(YankiAccount o) {
        return repository.delete(o);
    }

    @Override
    public Flux<YankiAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<YankiAccount> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<YankiAccount> findByOwnerIdentityNumber(String ownerIdentityNumber) {
        return repository.findByOwnerIdentityNumber(ownerIdentityNumber);
    }

    @Override
    public Mono<YankiAccount> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Mono<YankiAccount> findByImei(String imei) {
        return repository.findByImei(imei);
    }

    @Override
    public Mono<YankiAccount> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Mono<YankiAccount> validateAccount(YankiAccount account) {
        return repository
                .validate(
                        account.getOwnerIdentityNumber(),
                        account.getPhoneNumber(),
                        account.getImei(),
                        account.getEmail())
                .switchIfEmpty(Mono
                        .just(YankiAccount
                                .builder()
                                .ownerIdentityNumber(null)
                                .imei(null)
                                .email(null)
                                .phoneNumber(null)
                                .build())
                );
    }
}
