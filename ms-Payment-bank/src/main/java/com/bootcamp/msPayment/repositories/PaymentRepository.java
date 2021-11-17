package com.bootcamp.msPayment.repositories;

import com.bootcamp.msPayment.models.entities.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * The interface Payment repository.
 */
public interface PaymentRepository extends ReactiveMongoRepository<Payment,String> {
}
