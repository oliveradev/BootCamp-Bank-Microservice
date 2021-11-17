package com.bootcamp.msConsumptions.services.impl;

import com.bootcamp.msConsumptions.models.dto.CreditCardDTO;
import com.bootcamp.msConsumptions.models.dto.TransactionActiveDTO;
import com.bootcamp.msConsumptions.services.ITransactionDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * The type Transaction active service.
 */
@Service
public class TransactionActiveServiceImpl implements ITransactionDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionActiveServiceImpl.class);

    private WebClient.Builder client;

    @Override
    public Mono<TransactionActiveDTO> saveTransaction(TransactionActiveDTO transaction) {
        LOGGER.info("initializing Transaction create");

        return client
                .baseUrl("http://localhost:9001/api/transaction")
                .build()
                .post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transaction)
                .retrieve()
                .bodyToMono(TransactionActiveDTO.class);
    }

}
