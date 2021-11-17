package com.bootcamp.creditcardservice.repositories;

import com.bootcamp.creditcardservice.documents.entities.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {
    /**
     * Find by pan mono.
     *
     * @param pan the pan
     * @return the mono
     */
    Mono<CreditCard> findByPan (String pan);

    Mono<CreditCard> findByCustomer_CustomerIdentityNumber(String customerIdentityNumber);
}
