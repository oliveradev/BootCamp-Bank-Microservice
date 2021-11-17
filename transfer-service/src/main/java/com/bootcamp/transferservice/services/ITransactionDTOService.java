package com.bootcamp.transferservice.services;

import com.bootcamp.transferservice.documents.dto.TransactionDTO;
import reactor.core.publisher.Mono;

public interface ITransactionDTOService {
    public Mono<TransactionDTO> saveTransaction(TransactionDTO transaction);
}
