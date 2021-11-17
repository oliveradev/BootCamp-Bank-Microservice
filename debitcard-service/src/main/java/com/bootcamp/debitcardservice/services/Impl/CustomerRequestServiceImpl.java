package com.bootcamp.debitcardservice.services.Impl;

import com.bootcamp.debitcardservice.documents.dto.CustomerCommand;
import com.bootcamp.debitcardservice.documents.dto.CustomerRequest;
import com.bootcamp.debitcardservice.services.ICustomerRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerRequestServiceImpl implements ICustomerRequestService {
    private static final Logger log = LoggerFactory.getLogger(CustomerRequestServiceImpl.class);

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public Mono<CustomerRequest> getCustomer(String customerIdentityNumber) {
        return webClientBuilder
                .baseUrl("http://localhost:9000/api/customer")
                .build()
                .get()
                .uri("/findCustomerCredit/{customerIdentityNumber}", customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(CustomerRequest.class))
                .doOnNext(c -> log.info("Customer response: {}",c.getName()));
    }
}
