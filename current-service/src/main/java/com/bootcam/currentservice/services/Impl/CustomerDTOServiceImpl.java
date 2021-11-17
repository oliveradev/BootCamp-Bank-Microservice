package com.bootcam.currentservice.services.Impl;

import com.bootcam.currentservice.models.dto.Customer;
import com.bootcam.currentservice.services.ICustomerDTOService;
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
public class CustomerDTOServiceImpl implements ICustomerDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDTOServiceImpl.class);

    @Autowired
    @Qualifier("client")
    private WebClient.Builder client;


    @Override
    public Mono<Customer> getCustomer(String customerIdentityNumber){
        Map<String, Object> params = new HashMap<String,Object>();
        LOGGER.info("initializing client query");
        params.put("customerIdentityNumber",customerIdentityNumber);
        return client
                .baseUrl("http://localhost:9000/api/customer")
                .build()
                .get()
                .uri("/findCustomerCredit/{customerIdentityNumber}",customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Customer.class))
                .doOnNext(c -> LOGGER.info("Customer Response: Customer={}", c.getName()));
    }

    @Override
    public Mono<Customer> newPan(String id, Customer customer) {
        LOGGER.info("initializing Customer cards");
        return client
                .baseUrl("http://localhost:9000/api/customer")
                .build()
                .put()
                .uri("/cards/{id}", Collections.singletonMap("id", id))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(customer)
                .retrieve()
                .bodyToMono(Customer.class)
                .doOnNext(c -> LOGGER.info("Customer Response: Customer={}", c.getName()));
    }
}