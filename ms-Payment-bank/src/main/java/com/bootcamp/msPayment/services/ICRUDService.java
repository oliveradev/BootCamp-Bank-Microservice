package com.bootcamp.msPayment.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Icrud service.
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 */
public interface ICRUDService<T,ID> {
    /**
     * Create mono.
     *
     * @param o the o
     * @return the mono
     */
    Mono<T> create(T o);

    /**
     * Find all flux.
     *
     * @return the flux
     */
    Flux<T> findAll();

    /**
     * Find by id mono.
     *
     * @param id the id
     * @return the mono
     */
    Mono<T> findById(ID id);

    /**
     * Update mono.
     *
     * @param id the id
     * @param o  the o
     * @return the mono
     */
    Mono<T> update(ID id, T o);

    /**
     * Delete mono.
     *
     * @param o the o
     * @return the mono
     */
    Mono<Void> delete(T o);
}
