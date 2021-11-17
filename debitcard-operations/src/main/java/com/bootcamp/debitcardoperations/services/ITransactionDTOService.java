package com.bootcamp.debitcardoperations.services;

import com.bootcamp.debitcardoperations.documents.dto.TransactionDTO;
import reactor.core.publisher.Mono;

public interface ITransactionDTOService {
    /**
     * Save transaction mono.
     *
     * @param transaction the transaction
     * @return the mono
     */
    public Mono<TransactionDTO> saveTransaction(TransactionDTO transaction);
}