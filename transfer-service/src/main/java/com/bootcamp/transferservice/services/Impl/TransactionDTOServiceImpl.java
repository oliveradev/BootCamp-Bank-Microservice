package com.bootcamp.transferservice.services.Impl;

import com.bootcamp.transferservice.documents.dto.TransactionDTO;
import com.bootcamp.transferservice.services.ITransactionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransactionDTOServiceImpl implements ITransactionDTOService {
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
