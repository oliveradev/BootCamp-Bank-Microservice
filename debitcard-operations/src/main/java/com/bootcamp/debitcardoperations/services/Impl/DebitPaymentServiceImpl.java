package com.bootcamp.debitcardoperations.services.Impl;

import com.bootcamp.debitcardoperations.documents.entities.DebitPayment;
import com.bootcamp.debitcardoperations.repositories.DebitPaymentRepository;
import com.bootcamp.debitcardoperations.services.IDebitPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitPaymentServiceImpl implements IDebitPaymentService {

    @Autowired
    private DebitPaymentRepository repository;

    /**
     * Create mono.
     *
     * @param o the o
     * @return the mono
     */
    @Override
    public Mono<DebitPayment> create(DebitPayment o) {
        return repository.save(o);
    }

    /**
     * Find all flux.
     *
     * @return the flux
     */
    @Override
    public Flux<DebitPayment> findAll() {
        return repository.findAll();
    }

    /**
     * Find by id mono.
     *
     * @param s the id
     * @return the mono
     */
    @Override
    public Mono<DebitPayment> findById(String s) {
        return repository.findById(s);
    }

    /**
     * Delete mono.
     *
     * @param o the o
     * @return the mono
     */
    @Override
    public Mono<Void> delete(DebitPayment o) {
        return repository.delete(o);
    }

    /**
     * Update mono.
     *
     * @param o the o
     * @return the mono
     */
    @Override
    public Mono<DebitPayment> update(DebitPayment o) {
        return repository.save(o);
    }

    @Override
    public Flux<DebitPayment> findAllByOriginAccount(String originAccount) {
        return repository.findAllByOriginAccount(originAccount);
    }
}
