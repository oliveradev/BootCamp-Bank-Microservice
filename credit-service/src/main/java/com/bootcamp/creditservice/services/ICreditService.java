package com.bootcamp.creditservice.services;

import com.bootcamp.creditservice.models.dto.Customer;
import com.bootcamp.creditservice.models.entities.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService extends ICrudService<Credit, String> {
    Mono<Credit> findByContractNumber(String contractNumber);

    /**
     * Gets customer.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the customer
     */
    Mono<Customer> getCustomer(String customerIdentityNumber);

    /**
     * Find all by customer identity number flux.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the flux
     */
    Flux<Credit> findAllByCustomerIdentityNumber(String customerIdentityNumber);

    /**
     * Validate customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    Mono<Credit> validateCustomerIdentityNumber(String customerIdentityNumber);



}
