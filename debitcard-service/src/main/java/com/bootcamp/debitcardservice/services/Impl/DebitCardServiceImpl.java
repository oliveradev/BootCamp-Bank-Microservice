package com.bootcamp.debitcardservice.services.Impl;

import com.bootcamp.debitcardservice.documents.entities.DebitCard;
import com.bootcamp.debitcardservice.repositories.DebitCardRepository;
import com.bootcamp.debitcardservice.services.IDebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitCardServiceImpl implements IDebitCardService {

    @Autowired
    DebitCardRepository repository;

    @Override
    public Mono<DebitCard> create(DebitCard o) {
        return repository.save(o);
    }

    @Override
    public Flux<DebitCard> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<DebitCard> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<DebitCard> update(DebitCard o) {
        return repository.save(o);
    }

    @Override
    public Mono<Void> delete(DebitCard o) {
        return repository.delete(o);
    }

    @Override
    public Mono<DebitCard> findByPan(String pan) {
        return repository.findByPan(pan);
    }

    @Override
    public Mono<DebitCard> findDebitCardByCustomer_CustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findDebitCardByCustomer_CustomerIdentityNumber(customerIdentityNumber);
    }
}
