package com.bootcamp.withdrawservice.services;

import com.bootcamp.withdrawservice.documents.dto.TransactionCommand;
import reactor.core.publisher.Mono;

public interface ITransactionService {
    Mono<TransactionCommand> saveTransaction(TransactionCommand transaction);
}
