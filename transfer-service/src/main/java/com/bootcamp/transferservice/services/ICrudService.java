package com.bootcamp.transferservice.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICrudService <T, ID>{

    Mono<T> create(T o);


    Flux<T> findAll();


    Mono<T> findById(ID id);


    Mono<Void> delete(T o);


    Mono<T> update(T o);
}
