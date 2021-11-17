package com.bootcamp.creditcardservice.handlers;

import com.bootcamp.creditcardservice.documents.dto.CustomerDTO;
import com.bootcamp.creditcardservice.documents.entities.CreditCard;
import com.bootcamp.creditcardservice.services.ICreditCardService;
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
public class CreditCardHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardHandler.class);

    @Autowired
    private ICreditCardService service;

    /**
     * Find all mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), CreditCard.class);
    }

    /**
     * Find credit card mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findCreditCard(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> findCreditCardByCustomer(ServerRequest request) {
        String customerIdentityNumber = request.pathVariable("customerIdentityNumber");
        return service.findByCustomerIdentityNumber(customerIdentityNumber).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Find credit card by pan mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findCreditCardByPan(ServerRequest request) {
        String pan = request.pathVariable("pan");
        return service.findByPan(pan).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * New credit card mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> newCreditCard(ServerRequest request){

        Mono<CreditCard> creditCardMono = request.bodyToMono(CreditCard.class);
        String customerIdentityNumber = request.pathVariable("customerIdentityNumber");

        return creditCardMono.flatMap( creditCard -> service.getCustomer(customerIdentityNumber)
                        .flatMap(customer -> {
                            LOGGER.info("Veamos: " + customer.getName());
                            creditCard.setCustomer(CustomerDTO.builder().name(customer.getName())
                                    .code(customer.getCustomerType().getCode())
                                    .customerIdentityNumber(customer.getCustomerIdentityNumber()).build());
                            return service.validateCustomerIdentityNumber(customer.getCustomerIdentityNumber())
                                    .flatMap(creditcardFound -> {
                                        if(creditcardFound.getPan() != null){
                                            LOGGER.info("La tarjeta de crédito encontrada es: "
                                                    + creditcardFound.getPan());
                                            return Mono.empty();
                                        }else {
                                            LOGGER.info("No se encontró la cuenta ");
                                            return service.create(creditCard);
                                        }
                                    });
                        })
                ).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Delete credit card mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> deleteCreditCard(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<CreditCard> creditCardMono = service.findById(id);

        return creditCardMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Update credit card mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> updateCreditCard(ServerRequest request){
        Mono<CreditCard> creditCardMono = request.bodyToMono(CreditCard.class);
        String id = request.pathVariable("id");

        return service.findById(id).zipWith(creditCardMono, (db,req) -> {
                    db.setTotalConsumption(req.getTotalConsumption());
                    db.setBalanceAmount(req.getBalanceAmount());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),CreditCard.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
