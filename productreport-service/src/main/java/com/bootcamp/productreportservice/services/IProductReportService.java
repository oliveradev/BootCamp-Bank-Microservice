package com.bootcamp.productreportservice.services;

import com.bootcamp.productreportservice.documents.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductReportService {

    Mono<CustomerRequest> getCustomer(String customerIdentityNumber);
    Flux<Credit> getCredit(String customerIdentityNumber);
    Mono<Creditcard> getCreditCard(String customerIdentityNumber);
    Mono<SavingAccount> getSavingAccount(String customerIdentityNumber);
    Flux<Current> getCurrentAccount(String customerIdentityNumber);
    Mono<FixedTermAccount> getFixedTermAccount(String customerIdentityNumber);

}
