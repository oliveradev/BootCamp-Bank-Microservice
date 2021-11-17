package com.bootcamp.productreportservice.services.Impl;

import com.bootcamp.productreportservice.documents.dto.*;
import com.bootcamp.productreportservice.services.IProductReportService;
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
public class ProductReportServiceImpl implements IProductReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReportServiceImpl.class);

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public Mono<CustomerRequest> getCustomer(String customerIdentityNumber) {
        return webClientBuilder.baseUrl("http://localhost:9000/customer")
                .build()
                .get()
                .uri("/findCustomerCredit/{customerIdentityNumber}", customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(CustomerRequest.class))
                .doOnNext(c -> LOGGER.info("Customer Response: Customer={}", c.getName()));
    }

    @Override
    public Flux<Credit> getCredit(String customerIdentityNumber) {
        Map<String, Object> params = new HashMap<>();
        LOGGER.info("Searching Credit by: {}" + customerIdentityNumber);
        params.put("customerIdentityNumber", customerIdentityNumber);
        return webClientBuilder.baseUrl("http://localhost:9003/api/credit")
                .build()
                .get()
                .uri("/customer/{customerIdentityNumber}", customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Credit.class))
                .switchIfEmpty(Mono.just(Credit.builder().contractNumber(null).build()))
                .doOnNext(c -> LOGGER.info("Credit Response: Credit={}", c.getContractNumber()));
    }

    @Override
    public Mono<Creditcard> getCreditCard(String customerIdentityNumber) {
        Map<String, Object> params = new HashMap<>();
        LOGGER.info("Searching CreditCard by: {}" + customerIdentityNumber);
        params.put("customerIdentityNumber", customerIdentityNumber);
        return webClientBuilder.baseUrl("http://localhost:9006/api/creditcard")
                .build()
                .get()
                .uri("/customer/{customerIdentityNumber}", customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Creditcard.class))
                .switchIfEmpty(Mono.just(Creditcard.builder().pan(null).build()))
                .doOnNext(c -> LOGGER.info("CreditCard Response: CreditCard={}", c.getPan()));
    }

    @Override
    public Mono<SavingAccount> getSavingAccount(String customerIdentityNumber) {

        Map<String, Object> params = new HashMap<>();
        LOGGER.info("Searching SavingAccount by: {}" + customerIdentityNumber);
        params.put("customerIdentityNumber", customerIdentityNumber);
        return webClientBuilder.baseUrl("http://localhost:9002/api/savingAccount")
                .build()
                .get()
                .uri("/{customerIdentityNumber}", customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(SavingAccount.class))
                .switchIfEmpty(Mono.just(SavingAccount.builder().accountNumber(null).build()))
                .doOnNext(c -> LOGGER.info("SavingAccount Response: savingaccount={}", c.getAccountNumber()));
    }

    @Override
    public Flux<Current> getCurrentAccount(String customerIdentityNumber) {
        Map<String, Object> params = new HashMap<>();
        LOGGER.info("Searching CurrentAccount by: {}" + customerIdentityNumber);
        params.put("customerIdentityNumber", customerIdentityNumber);
        return webClientBuilder.baseUrl("http://localhost:9004/api/currentAccount")
                .build()
                .get()
                .uri("/{customerIdentityNumber}", customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Current.class))
                .switchIfEmpty(Mono.just(Current.builder().accountNumber(null).build()))
                .doOnNext(c -> LOGGER.info("CurrentAccount Response: CurrentAccount={}", c.getAccountNumber()));
    }

    @Override
    public Mono<FixedTermAccount> getFixedTermAccount(String customerIdentityNumber) {
        Map<String, Object> params = new HashMap<>();
        LOGGER.info("Searching FixedTermAccount by: {}" + customerIdentityNumber);
        params.put("customerIdentityNumber", customerIdentityNumber);
        return webClientBuilder.baseUrl("http://localhost:9005/api/fixedTermAccound")
                .build()
                .get()
                .uri("/{customerIdentityNumber}", customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(FixedTermAccount.class))
                .switchIfEmpty(Mono.just(FixedTermAccount.builder().accountNumber(null).build()))
                .doOnNext(c -> LOGGER.info("FixedTermAccount Response: FixedTermAccount={}", c.getAccountNumber()));

    }
}
