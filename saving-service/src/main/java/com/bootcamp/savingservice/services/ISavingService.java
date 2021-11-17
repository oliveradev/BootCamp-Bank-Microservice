package com.bootcamp.savingservice.services;

import com.bootcamp.savingservice.documents.entities.Account;
import reactor.core.publisher.Mono;

public interface ISavingService extends ICrudService<Account, String>  {
    /**
     * Find by customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    public Mono<Account> findByCustomerIdentityNumber(String customerIdentityNumber);

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
    Mono<Account> findByAccountNumber(String accountNumber);
}
