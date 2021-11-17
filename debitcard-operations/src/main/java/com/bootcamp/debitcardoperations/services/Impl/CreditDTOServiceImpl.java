package com.bootcamp.debitcardoperations.services.Impl;

import com.bootcamp.debitcardoperations.documents.dto.CreditDTO;
import com.bootcamp.debitcardoperations.services.ICreditDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreditDTOServiceImpl implements ICreditDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditDTOServiceImpl.class);

    @Qualifier
    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public Mono<CreditDTO> updateCredit(CreditDTO credit) {
        LOGGER.info("initializing Credit Update");

        return webClientBuilder.baseUrl("http://localhost:9003/api/credit")
                .build()
                .put()
                .uri("/{contractNumber}", Collections.singletonMap("contractNumber",credit.getContractNumber()))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(credit)
                .retrieve()
                .bodyToMono(CreditDTO.class);
    }

    @Override
    public Mono<CreditDTO> findCredit(String contractNumber) {
        Map<String, Object> params = new HashMap<String,Object>();
        LOGGER.info("initializing Credit query");
        params.put("contractNumber",contractNumber);
        return webClientBuilder.baseUrl("http://localhost:9003/api/credit")
                .build()
                .get()
                .uri("/{contractNumber}",params)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(CreditDTO.class))
                .doOnNext(c -> LOGGER.info("Credit Response: Credit Amounth={}", c.getAmount()));
    }
}
