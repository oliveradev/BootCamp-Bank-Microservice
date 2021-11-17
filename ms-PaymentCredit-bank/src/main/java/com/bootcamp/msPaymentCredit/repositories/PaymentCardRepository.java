package com.bootcamp.msPaymentCredit.repositories;

import com.bootcamp.msPaymentCredit.models.entities.PaymentCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * The interface Payment card repository.
 */
public interface PaymentCardRepository extends ReactiveMongoRepository<PaymentCard,String> {
}
