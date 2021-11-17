package com.bootcamp.debitcardservice.services;

import com.bootcamp.debitcardservice.documents.entities.DebitCard;
import reactor.core.publisher.Mono;

public interface IDebitCardService extends ICrudService<DebitCard, String>{

    Mono<DebitCard> findByPan(String pan);
    Mono<DebitCard> findDebitCardByCustomer_CustomerIdentityNumber(String customerIdentityNumber);
}
