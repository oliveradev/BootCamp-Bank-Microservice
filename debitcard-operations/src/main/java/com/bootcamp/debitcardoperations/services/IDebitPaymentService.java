package com.bootcamp.debitcardoperations.services;

import com.bootcamp.debitcardoperations.documents.entities.DebitPayment;
import reactor.core.publisher.Flux;

public interface IDebitPaymentService extends ICRUDService<DebitPayment,String>{

    Flux<DebitPayment> findAllByOriginAccount (String originAccount);
}
