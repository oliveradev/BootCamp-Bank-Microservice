package com.bootcamp.fixedtermservice.services;

import com.bootcamp.fixedtermservice.models.dto.Customer;
import com.bootcamp.fixedtermservice.models.entities.FixedTermAccount;
import reactor.core.publisher.Mono;

public interface IFixedTermAccountService extends ICrudService<FixedTermAccount, String> {

    /**
     * Gets customer.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the customer
     */
    public Mono<Customer> getCustomer(String customerIdentityNumber);

    /**
     * Validate customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    public Mono<FixedTermAccount> validateCustomerIdentityNumber(String customerIdentityNumber);

    /**
     * Find by customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    public Mono<FixedTermAccount> findByCustomerIdentityNumber(String customerIdentityNumber);

    /**
     * Find by account number mono.
     *
     * @param accountNumber the account number
     * @return the mono
     */
    public Mono<FixedTermAccount> findByAccountNumber(String accountNumber);
}
