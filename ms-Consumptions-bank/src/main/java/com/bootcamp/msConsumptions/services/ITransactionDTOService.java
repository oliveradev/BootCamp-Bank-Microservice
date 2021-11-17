package com.bootcamp.msConsumptions.services;

import com.bootcamp.msConsumptions.models.dto.TransactionActiveDTO;
import reactor.core.publisher.Mono;

/**
 * The interface Transaction dto service.
 */
public interface ITransactionDTOService {
    /**
     * Save transaction mono.
     *
     * @param transaction the transaction
     * @return the mono
     */
    public Mono<TransactionActiveDTO> saveTransaction(TransactionActiveDTO transaction);
}
