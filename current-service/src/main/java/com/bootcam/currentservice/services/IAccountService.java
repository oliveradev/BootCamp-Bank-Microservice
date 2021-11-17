package com.bootcam.currentservice.services;

import com.bootcam.currentservice.models.entities.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Account service.
 */
public interface IAccountService extends ICrudService<Account,String> {
    /**
     * Find by customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    Flux<Account> findAllByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<Account> findByCustomerIdentityNumber(String customerIdentityNumber);

    /**
     * Validate customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    Mono<Account> validateCustomerIdentityNumber(String customerIdentityNumber);

    /**
     * Find by account number mono.
     *
     * @param accountNumber the account number
     * @return the mono
     */
    public Mono<Account> findByAccountNumber(String accountNumber);
}

