package com.bootcamp.creditservice.services.Impl;

import com.bootcamp.creditservice.models.dto.Customer;
import com.bootcamp.creditservice.models.entities.Credit;
import com.bootcamp.creditservice.repositories.CreditRepository;
import com.bootcamp.creditservice.services.ICreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {

    private static final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    CreditRepository repository;

    @Override
    public Mono<Credit> findByContractNumber(String contractNumber) {
        return repository.findByContractNumber(contractNumber);
    }

    @Override
    public Mono<Customer> getCustomer(String customerIdentityNumber) {
        return webClientBuilder.build()
                .get()
                .uri("localhost:9000/api/customer/findCustomerCredit/"+customerIdentityNumber)
                .retrieve()
                .bodyToMono(Customer.class)
                .doOnNext(c -> log.info("Customer response: {}", c.getName()) );
    }

    @Override
    public Flux<Credit> findAllByCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findAllByCustomerIdentityNumber(customerIdentityNumber);
    }

    @Override
    public Mono<Credit> validateCustomerIdentityNumber(String customerIdentityNumber) {
        return repository.findByCustomerIdentityNumber(customerIdentityNumber)
                .switchIfEmpty(Mono.just(Credit.builder().customerIdentityNumber(null).build()));
    }

    @Override
    public Mono<Credit> create(Credit o) {
        return repository.save(o);
    }

    @Override
    public Flux<Credit> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Credit> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Credit> update(Credit o) {
        return repository.save(o);
    }

    @Override
    public Mono<Void> delete(Credit o) {
        return repository.delete(o);
    }
}
