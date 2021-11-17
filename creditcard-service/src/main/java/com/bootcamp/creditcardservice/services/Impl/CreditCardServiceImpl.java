package com.bootcamp.creditcardservice.services.Impl;


import com.bootcamp.creditcardservice.documents.dto.Customer;
import com.bootcamp.creditcardservice.documents.dto.CustomerDTO;
import com.bootcamp.creditcardservice.documents.entities.CreditCard;
import com.bootcamp.creditcardservice.repositories.CreditCardRepository;
import com.bootcamp.creditcardservice.services.ICreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreditCardServiceImpl implements ICreditCardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    @Autowired
    CreditCardRepository repository;

    @Autowired
    WebClient.Builder webClientBuilder;


    @Override
    public Mono<Customer> getCustomer(String customerIdentityNumber){
        Map<String, Object> params = new HashMap<String,Object>();
        LOGGER.info("initializing client query");
        params.put("customerIdentityNumber",customerIdentityNumber);
        return webClientBuilder
                .baseUrl("http://localhost:9000/api/customer")
                .build()
                .get()
                .uri("/findCustomerCredit/{customerIdentityNumber}",customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Customer.class))
                .doOnNext(c -> LOGGER.info("Customer Response: Customer={}", c.getName()));
    }

    /**
     * Find by pan mono.
     *
     * @param pan the pan
     * @return the mono
     */
    @Override
    public Mono<CreditCard> findByPan(String pan) {
        return repository.findByPan(pan);
    }

    /**
     * New pan mono.
     *
     * @param id          the id
     * @param customerDTO the customer dto
     * @return the mono
     *
     */
    @Override
    public Mono<CustomerDTO> newPan(String id, CustomerDTO customerDTO) {
        LOGGER.info("initializing Customer cards");
        return webClientBuilder
                .baseUrl("http://localhost:9000/api/customer")
                .build()
                .put()
                .uri("/cards/{id}", Collections.singletonMap("id", id))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(customerDTO)
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .doOnNext(c -> LOGGER.info("Customer Response: Customer={}", c.getName()));
    }

    @Override
    public Mono<CreditCard> validateCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findByCustomer_CustomerIdentityNumber(customerIdentityNumber)
                .switchIfEmpty(Mono.just(CreditCard.builder()
                        .pan(null).build()));
    }

    @Override
    public Mono<CreditCard> findByCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findByCustomer_CustomerIdentityNumber(customerIdentityNumber);
    }

    /**
     * Create mono.
     *
     * @param o the o
     * @return the mono
     */
    @Override
    public Mono<CreditCard> create(CreditCard o) {
        return repository.save(o);
    }

    /**
     * Find all flux.
     *
     * @return the flux
     */
    @Override
    public Flux<CreditCard> findAll() {
        return repository.findAll();
    }

    /**
     * Find by id mono.
     *
     * @param s the s
     * @return the mono
     */
    @Override
    public Mono<CreditCard> findById(String s) {
        return repository.findById(s);
    }

    /**
     * Update mono.
     *
     * @param o the o
     * @return the mono
     */
    @Override
    public Mono<CreditCard> update(CreditCard o) {
        return repository.save(o);
    }

    /**
     * Delete mono.
     *
     * @param o the o
     * @return the mono
     */
    @Override
    public Mono<Void> delete(CreditCard o) {
        return repository.delete(o);
    }

    /**
     * Get customer mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */



}
