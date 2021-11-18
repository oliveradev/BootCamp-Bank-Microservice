package com.bootcamp.bootcoinservice.services;

import com.bootcamp.bootcoinservice.documents.dto.BootCoinTransactionDTO;
import reactor.core.publisher.Mono;

public interface ITransactionBootCoinService {
    Mono<BootCoinTransactionDTO> save(BootCoinTransactionDTO transactionDTO);
}
