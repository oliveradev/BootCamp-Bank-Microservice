package com.bootcamp.msPaymentCredit.services;

import com.bootcamp.msPaymentCredit.models.dto.TransactionDTO;
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
    public Mono<TransactionDTO> saveTransaction(TransactionDTO transaction);
}
