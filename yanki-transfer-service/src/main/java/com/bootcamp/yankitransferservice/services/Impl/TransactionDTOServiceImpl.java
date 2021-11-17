package com.bootcamp.yankitransferservice.services.Impl;

import com.bootcamp.yankitransferservice.documents.dto.TransactionDTO;
import com.bootcamp.yankitransferservice.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransactionDTOServiceImpl implements ITransactionService {

    @Autowired
    private WebClient.Builder client;

    @Override
    public Mono<TransactionDTO> save(TransactionDTO transaction) {
        return  client
                .build()
                .post()
                .uri("localhost:9016/api/yanki-transaction")
                .body(Mono.just(transaction), TransactionDTO.class)
                .retrieve()
                .bodyToMono(TransactionDTO.class);
    }
}
