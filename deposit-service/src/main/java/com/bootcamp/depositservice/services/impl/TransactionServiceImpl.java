package com.bootcamp.depositservice.services.impl;

import com.bootcamp.depositservice.models.dto.TransactionDTO;
import com.bootcamp.depositservice.services.ITransactionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements ITransactionDTOService {

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public Mono<TransactionDTO> saveTransaction(TransactionDTO transaction) {
        return webClientBuilder
                .build()
                .post()
                .uri("localhost:9003/api/transaction")
                .body(Mono.just(transaction), TransactionDTO.class)
                .retrieve()
                .bodyToMono(TransactionDTO.class);
    }
}
