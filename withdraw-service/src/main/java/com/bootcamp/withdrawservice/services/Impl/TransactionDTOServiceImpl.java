package com.bootcamp.withdrawservice.services.Impl;

import com.bootcamp.withdrawservice.documents.dto.TransactionCommand;
import com.bootcamp.withdrawservice.services.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransactionDTOServiceImpl implements ITransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebitAccountDTOServiceImpl.class);

    @Autowired
    private WebClient.Builder client;

    @Override
    public Mono<TransactionCommand> saveTransaction(TransactionCommand transaction) {
        return client
                .baseUrl("http://localhost:9001/api/transaction")
                .build()
                .post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transaction)
                .retrieve()
                .bodyToMono(TransactionCommand.class);
    }
}
