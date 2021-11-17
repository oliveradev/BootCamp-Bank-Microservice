package com.bootcamp.debitcardservice.services;

import com.bootcamp.debitcardservice.documents.dto.CustomerRequest;
import reactor.core.publisher.Mono;

public interface ICustomerRequestService {
    Mono<CustomerRequest> getCustomer(String customerIdentityNumber);
}
