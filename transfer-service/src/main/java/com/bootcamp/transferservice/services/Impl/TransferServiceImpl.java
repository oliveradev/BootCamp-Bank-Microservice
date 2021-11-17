package com.bootcamp.transferservice.services.Impl;

import com.bootcamp.transferservice.documents.entities.Transfer;
import com.bootcamp.transferservice.repositories.TransferRepository;
import com.bootcamp.transferservice.services.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransferServiceImpl implements ITransferService {
    @Autowired
    private TransferRepository repository;

    @Override
    public Mono<Transfer> create(Transfer o) {
        return repository.save(o);
    }

    @Override
    public Flux<Transfer> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Transfer> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Void> delete(Transfer o) {
        return repository.delete(o);
    }

    @Override
    public Mono<Transfer> update(Transfer o) {
        return repository.save(o);
    }
}
