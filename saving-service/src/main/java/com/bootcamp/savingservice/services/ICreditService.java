package com.bootcamp.savingservice.services;

import com.bootcamp.savingservice.documents.dto.Credit;
import com.bootcamp.savingservice.documents.dto.Creditcard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {
    Mono<Boolean> validateDebtorCredit(String customerIdentityNumber);

    Flux<Credit> getCredit(String customerIdentityNumber);

    Mono<Creditcard> getCreditcard(String customerIdentityNumber);
}
