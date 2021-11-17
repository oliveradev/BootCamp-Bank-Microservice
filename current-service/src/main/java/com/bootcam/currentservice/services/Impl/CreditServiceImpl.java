package com.bootcam.currentservice.services.Impl;

import com.bootcam.currentservice.models.dto.Credit;
import com.bootcam.currentservice.models.dto.CreditCard;
import com.bootcam.currentservice.services.ICreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CreditServiceImpl implements ICreditService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditServiceImpl.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Flux<Credit> getCredit(String customerIdentityNumber) {
        Map<String, Object> params = new HashMap<String,Object>();
        LOGGER.info("initializing credit query");
        params.put("customerIdentityNumber",customerIdentityNumber);
        return webClientBuilder
                .baseUrl("http://localhost:9003/api/credit")
                .build()
                .get()
                .uri("/customer/{customerIdentityNumber}",customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Credit.class))
                .switchIfEmpty(Flux.just(Credit.builder()
                        .debtor(false).build()))
                .doOnNext(c -> LOGGER.info("Credit Response: Contract={}", c.getContractNumber()));
    }

    @Override
    public Mono<CreditCard> getCreditcard(String customerIdentityNumber) {
        Map<String, Object> params = new HashMap<String,Object>();
        LOGGER.info("initializing creditcard query");
        params.put("customerIdentityNumber",customerIdentityNumber);
        return webClientBuilder
                .baseUrl("http://localhost:9005/api/creditcard")
                .build()
                .get()
                .uri("/customer/{customerIdentityNumber}",customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(CreditCard.class))
                .switchIfEmpty(Mono.just(CreditCard.builder()
                        .debtor(false).build()))
                .doOnNext(c -> LOGGER.info("CreditCard Response: Pan={}", c.getPan()));
    }

    @Override
    public Mono<Boolean> validateDebtorCredit(String customerIdentityNumber) {

        Mono<List<Credit>> credit = getCredit(customerIdentityNumber)
                .filter(creditFound -> creditFound.isDebtor()).collectList();
        Mono<CreditCard> creditcard= getCreditcard(customerIdentityNumber);

        return Mono.zip(credit,creditcard, (a,b) -> {

            if(a.size()>0){
                return true;
            }else if (b.isDebtor()){
                return true;
            } else return false;
        }).doOnNext(value -> LOGGER.info("the value of Debtor validate is: {}"+ value));

        // other form
//        Mono<Creditcard> creditcard= getCreditcard(customerIdentityNumber);
//
//        return getCredit(customerIdentityNumber).zipWith(creditcard, (a,b) -> {
//
//            if(a.isDebtor()){
//                return true;
//            }else if (b.isDebtor()){
//                return true;
//            } else return false;
//        }).doOnNext(value -> LOGGER.info("the value of Debtor validate is: {}"+ value));
//    }
    }
}
