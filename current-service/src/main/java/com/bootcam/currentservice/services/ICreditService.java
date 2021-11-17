package com.bootcam.currentservice.services;

import com.bootcam.currentservice.models.dto.Credit;
import com.bootcam.currentservice.models.dto.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {
    Mono<Boolean> validateDebtorCredit(String customerIdentityNumber);

    Flux<Credit> getCredit(String customerIdentityNumber);

    Mono<CreditCard> getCreditcard(String customerIdentityNumber);
}
