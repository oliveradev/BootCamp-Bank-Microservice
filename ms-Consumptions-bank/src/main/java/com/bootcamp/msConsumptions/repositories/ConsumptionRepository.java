package com.bootcamp.msConsumptions.repositories;

import com.bootcamp.msConsumptions.models.entities.Consumption;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * The interface Consumption repository.
 */
public interface ConsumptionRepository extends ReactiveMongoRepository<Consumption,String> {
}
