package com.bootcamp.savingservice.services.Impl;

import com.bootcamp.savingservice.documents.dto.CustomerQuery;
import com.bootcamp.savingservice.services.ICustomerRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerQueryServiceImpl implements ICustomerRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerQueryServiceImpl.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<CustomerQuery> getCustomer(String customerIdentityNumber) {

        return webClientBuilder
                .build()
                .get()
                .uri("localhost:9000/api/customer/findCustomerCredit/"+customerIdentityNumber)
                .retrieve()
                .bodyToMono(CustomerQuery.class)
                .doOnNext(c->LOGGER.info("Customer Response: Customer={}", c.getName()));

    }


}
