package com.bootcamp.creditservice.handlers;

import com.bootcamp.creditservice.models.dto.CustomerDTO;
import com.bootcamp.creditservice.models.entities.Credit;
import com.bootcamp.creditservice.services.ICreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CreditHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditHandler.class);


    @Autowired
    private ICreditService service;

    public Mono<ServerResponse> findAll(final ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Credit.class);
    }

    public Mono<ServerResponse> findCredit(final ServerRequest request){
        String contractNumber = request.pathVariable("contractNumber");
        return service.findByContractNumber(contractNumber)
                .flatMap(c ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(c))
                                .switchIfEmpty(ServerResponse.notFound().build())
                        );
    }

    public Mono<ServerResponse> createCredit (final ServerRequest request){
        Mono<Credit> creditMono = request.bodyToMono(Credit.class);

        return creditMono.flatMap(credito ->{
            return service.getCustomer(credito.getCustomerIdentityNumber())
                    .flatMap(customer -> {
                        LOGGER.info("Customer:{} ", customer.toString());
                        credito.setAmount(credito.getCapital()
                        +credito.getCapital()* credito.getInterestRate()
                        +credito.getCommission());
                        credito.setCustomer(CustomerDTO.builder()
                                .name(customer.getName())
                                .code(customer.getCustomerType().getCode())
                                .customerIdentityNumber(customer.getCustomerIdentityNumber())
                                .build());
                        credito.setAmountInitial(credito.getAmount());
                        return service.validateCustomerIdentityNumber(customer.getCustomerIdentityNumber())
                                .flatMap(accountFound -> {
                                   if(accountFound.getCustomerIdentityNumber() != null
                                           &&(customer.getCustomerType().getCode().equals("TP-01"))
                                             || customer.getCustomerType().getCode().equals("TP-03")){
                                            LOGGER.info("La cuenta encontrada es: "
                                            +accountFound.getCustomerIdentityNumber());
                                            return Mono.empty();
                                   }else{
                                       return service.create(credito);
                                   }
                                });
                    });
        }).flatMap( c-> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> findAllByCustomerIdentityNumber(final ServerRequest request){
        String customerIdentityNumber = request.pathVariable("customerIdentityNumber");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllByCustomerIdentityNumber(customerIdentityNumber), Credit.class);
    }

    public Mono<ServerResponse> deleteCredit(final ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Credit> creditMono = service.findById(id);

        return creditMono
                .doOnNext(c-> LOGGER.info("Delete Credit: {}",c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateCredit(final ServerRequest request){
        Mono<Credit> creditMono = request.bodyToMono(Credit.class);

        String contractNumber = request.pathVariable("contractNumber");

        return service.findByContractNumber(contractNumber)
                .zipWith(creditMono, (db, req) ->{
                    db.setAmount(req.getAmount());
                    return db;
                }).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.update(c), Credit.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }



}
