package com.bootcamp.yankitransactionservice.handlers;

import com.bootcamp.yankitransactionservice.documents.Transaction;
import com.bootcamp.yankitransactionservice.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class TransactionHandler {

    @Autowired
    ITransactionService service;

    public Mono<ServerResponse> createTransaction(ServerRequest request){
        Mono<Transaction> monoTransaction = request.bodyToMono(Transaction.class);

        return monoTransaction.flatMap(transaction -> service.create(transaction))
                .flatMap(t -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(t)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }
}
