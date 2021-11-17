package com.bootcamp.yankitransferservice.services;

import com.bootcamp.yankitransferservice.documents.dto.TransactionDTO;
import reactor.core.publisher.Mono;

public interface ITransactionService {
    Mono<TransactionDTO> save(TransactionDTO transaction);
}
