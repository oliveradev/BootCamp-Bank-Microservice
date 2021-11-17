package com.bootcamp.debitcardoperations.repositories;

import com.bootcamp.debitcardoperations.documents.entities.DebitPayment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DebitPaymentRepository extends ReactiveMongoRepository<DebitPayment,String> {

    Flux<DebitPayment> findAllByOriginAccount (String originAccount);
}
