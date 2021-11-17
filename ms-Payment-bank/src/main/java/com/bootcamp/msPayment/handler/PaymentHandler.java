package com.bootcamp.msPayment.handler;

import com.bootcamp.msPayment.models.dto.TransactionDTO;
import com.bootcamp.msPayment.models.entities.Payment;
import com.bootcamp.msPayment.services.ICreditDTOService;
import com.bootcamp.msPayment.services.IPaymentService;
import com.bootcamp.msPayment.services.ITransactionDTOService;
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

/**
 * The type Payment handler.
 */
@Slf4j(topic = "payment_handler")
@Component
public class PaymentHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentHandler.class);

    @Autowired
    private IPaymentService service;

    @Autowired
    private ICreditDTOService creditService;

    @Autowired
    private ITransactionDTOService transactionService;

    /**
     * Find all mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Payment.class);
    }

    /**
     * Find payment mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findPayment(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    /**
     * New payment mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> newPayment(ServerRequest request){

        Mono<Payment> paymentMono = request.bodyToMono(Payment.class);
        return paymentMono.flatMap( paymentRequest -> creditService.findCredit(paymentRequest.getIdentityNumber())
                .flatMap(credit -> {
                credit.setAmount(credit.getAmount() - paymentRequest.getAmount());
                return creditService.updateCredit(credit);
                }).flatMap(creditTransaction -> {
                            TransactionDTO transaction = new TransactionDTO();
                            transaction.setTypeoftransaction("PAYMENT");
                            transaction.setTransactionAmount(paymentRequest.getAmount());
                            transaction.setCustomerIdentityNumber(paymentRequest.getCustomerIdentityNumber());
                            transaction.setIdentityNumber(paymentRequest.getIdentityNumber());
                            return transactionService.saveTransaction(transaction);
                        }).flatMap(payment ->  {
                    return service.create(paymentRequest);
                }))
                .flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }

    /**
     * Delete payment mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> deletePayment(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<Payment> paymentMono = service.findById(id);

        return paymentMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Update payment mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> updatePayment(ServerRequest request){
        Mono<Payment> paymentMono = request.bodyToMono(Payment.class);
        String id = request.pathVariable("id");

        return service.findById(id).zipWith(paymentMono, (db,req) -> {
                    db.setAmount(req.getAmount());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),Payment.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
