package com.bootcamp.msConsumptions.handler;

import com.bootcamp.msConsumptions.models.dto.TransactionActiveDTO;
import com.bootcamp.msConsumptions.models.entities.Consumption;
import com.bootcamp.msConsumptions.services.IConsumptionService;
import com.bootcamp.msConsumptions.services.ICreditCardDTOService;
import com.bootcamp.msConsumptions.services.ITransactionDTOService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * The type Consumption handler.
 */
@Slf4j(topic = "consumption_handler")
@Component
public class ConsumptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumptionHandler.class);

    @Autowired
    private IConsumptionService service;

    @Autowired
    private ITransactionDTOService transactionService;

    @Autowired
    private ICreditCardDTOService creditService;

    /**
     * Find all mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Consumption.class);
    }

    /**
     * Find consumption mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findConsumption(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    /**
     * New consumption mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> newConsumption(ServerRequest request){

            Mono<Consumption> consumptionMono = request.bodyToMono(Consumption.class);

        return consumptionMono.flatMap( consumptionRequest -> creditService.findByPan(consumptionRequest.getIdentityNumber())
                        .flatMap(credit -> {
                            if(credit.getCreditLimit()<consumptionRequest.getAmount()) {
                                return Mono.just(ServerResponse.badRequest());
                            }else if (credit.getBalanceAmount()>0.0){
                                consumptionRequest.setAmount(consumptionRequest.getAmount()-credit.getBalanceAmount());
                            }
                            credit.setTotalConsumption(credit.getTotalConsumption()+consumptionRequest.getAmount());
                            return creditService.updateCredit(credit);
                        }).flatMap(creditTransaction -> {
                            TransactionActiveDTO transaction = new TransactionActiveDTO();
                            transaction.setTypeoftransaction("CONSUMPTION");
                            transaction.setTransactionAmount(consumptionRequest.getAmount());
                            transaction.setIdentityNumber(consumptionRequest.getIdentityNumber());
                            transaction.setTransactionDescription(consumptionRequest.getDescription());
                            return transactionService.saveTransaction(transaction);
                        }).flatMap(consumption ->  {
                            return service.create(consumptionRequest);
                        }))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)));
          }

    /**
     * Delete consumption mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> deleteConsumption(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<Consumption> consumptionMono = service.findById(id);

        return consumptionMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Update consumption mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> updateConsumption(ServerRequest request){
        Mono<Consumption> consumptionMono = request.bodyToMono(Consumption.class);
        String id = request.pathVariable("id");

        return service.findById(id).zipWith(consumptionMono, (db,req) -> {
            db.setDescription(req.getDescription());
            db.setAmount(req.getAmount());
            return db;
        }).flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.create(c),Consumption.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
