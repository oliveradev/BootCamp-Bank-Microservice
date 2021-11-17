package com.bootcamp.savingservice.services;

import com.bootcamp.savingservice.documents.dto.CustomerQuery;
import reactor.core.publisher.Mono;

public interface ICustomerRequestService{
    /**
     * Gets customer.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the customer
     */
    public Mono<CustomerQuery> getCustomer(String customerIdentityNumber);
}
