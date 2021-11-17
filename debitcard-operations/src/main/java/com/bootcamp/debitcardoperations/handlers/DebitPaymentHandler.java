package com.bootcamp.debitcardoperations.handlers;


import com.bootcamp.debitcardoperations.documents.dto.TransactionDTO;
import com.bootcamp.debitcardoperations.documents.entities.DebitPayment;
import com.bootcamp.debitcardoperations.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@Component
public class DebitPaymentHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebitPaymentHandler.class);

    @Autowired
    private IDebitAccountDTOService debitService;

    @Autowired
    private IDebitPaymentService service;

    @Autowired
    private ITransactionDTOService transactionService;

    @Autowired
    private ICreditDTOService creditService;

    @Autowired
    private ICreditCardDTOService creditCardService;


    /**
     * Find all mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), DebitPayment.class);
    }

    /**
     * Find debitPayment mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findDebitPayment(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> findAllByOriginAccount(ServerRequest request) {
        String originAccount = request.pathVariable("originAccount");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllByOriginAccount(originAccount), DebitPayment.class);

    }

    /**
     * Create debitPayment mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> createDebitPayment(ServerRequest request){

        Mono<DebitPayment> debitPaymentMono = request.bodyToMono(DebitPayment.class);
        return debitPaymentMono.flatMap(debitpaymentRequest -> debitService.findByAccountNumber(debitpaymentRequest.getOriginTypeOfAccount(),debitpaymentRequest.getOriginAccount())
                        .flatMap(originAccount -> {
                            if(originAccount.getAmount()<debitpaymentRequest.getAmount()){
                                return Mono.error(new Throwable("THE AMOUNT OF TRANSFER EXCEDED THE AMOUNT OF ORIGIN ACCOUNT"));
                            }else if (debitpaymentRequest.getDestinationTypeOfCredit().equals("CREDITCARD")) {

                                return creditCardService.findByPan(debitpaymentRequest.getDestinationCredit())
                                        .flatMap(creditcard -> {
                                            if (creditcard.getTotalConsumption() > debitpaymentRequest.getAmount()) {
                                                creditcard.setTotalConsumption(creditcard.getTotalConsumption() - debitpaymentRequest.getAmount());

                                            } else {
                                                creditcard.setBalanceAmount(debitpaymentRequest.getAmount() - creditcard.getTotalConsumption());
                                                creditcard.setTotalConsumption(0.0);
                                            }
                                            return creditCardService.updateCredit(creditcard);
                                        });
                            } else if (debitpaymentRequest.getDestinationTypeOfCredit().equals("CREDIT")){
                                return creditService.findCredit(debitpaymentRequest.getDestinationCredit())
                                        .flatMap(credit -> {
                                            credit.setAmount(credit.getAmount() - debitpaymentRequest.getAmount());
                                            return creditService.updateCredit(credit);
                                        });
                            } else return Mono.error(new Throwable("THE TYPE OF CREDIT IS NOT CORRECT"));
                        })
                        .flatMap(destinationAccount -> debitService.findByAccountNumber(debitpaymentRequest.getOriginTypeOfAccount(),debitpaymentRequest.getOriginAccount())
                                .flatMap(originAccount -> {
                                    originAccount.setAmount(originAccount.getAmount()-debitpaymentRequest.getAmount());
                                    return debitService.updateDebit(originAccount.getTypeOfAccount(),originAccount);
                                }))
                        .flatMap(originAccount -> {
                            TransactionDTO transaction = new TransactionDTO();
                            transaction.setDestination(debitpaymentRequest.getDestinationCredit());
                            return transactionService.saveTransaction(TransactionDTO.builder()
                                    .typeoftransaction("PAYMENT").typeOfAccount(debitpaymentRequest.getOriginTypeOfAccount())
                                    .identityNumber(debitpaymentRequest.getOriginAccount()).customerIdentityNumber(originAccount.getCustomerIdentityNumber())
                                    .transactionAmount(debitpaymentRequest.getAmount()).destination(debitpaymentRequest.getDestinationCredit())
                                    .dateOperation(LocalDateTime.now())
                                    .transactionDescription("PAYMENT OF A ACTIVE PRODUCT BY A PASIVE PRODUCT").build());
                        })
                        .flatMap(transfer ->  service.create(debitpaymentRequest)))
                .flatMap( c -> ServerResponse
                        .created(URI.create("/api/debitPayment/origin/19198789115082"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Delete debitPayment mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> deleteDebitPayment(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<DebitPayment> transferMono = service.findById(id);

        return transferMono
                .doOnNext(c -> LOGGER.info("delete Transfer: TransferID={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
