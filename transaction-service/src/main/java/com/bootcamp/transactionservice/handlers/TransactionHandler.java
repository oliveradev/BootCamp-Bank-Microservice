package com.bootcamp.transactionservice.handlers;

import com.bootcamp.transactionservice.models.Transaction;
import com.bootcamp.transactionservice.services.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);

    @Autowired
    private ITransactionService service;

    /**
     * Find transactions by identity number mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findTransactionsByIdentityNumber(ServerRequest request){
        String identityNumber = request.pathVariable("identityNumber");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllByIdentityNumber(identityNumber), Transaction.class);
    }


    /**
     * Find all mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Transaction.class);
    }

    /**
     * Find all commissions mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findAllCommissions(ServerRequest request){
        String identityNumber = request.pathVariable("identityNumber");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().filter(transaction -> transaction.getTypeTransaction().equals("COMMISSION")
                                && transaction.getIdentityNumber().equals(identityNumber))
                        , Transaction.class);
    }

    public Mono<ServerResponse> reportLastTen(ServerRequest request){
        String identityNumber = request.pathVariable("identityNumber");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findFirst10ByIdentityNumberOrderByDateOperationDesc(identityNumber)
                        , Transaction.class);
    }

    /**
     * New transaction mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> newTransaction(ServerRequest request){
        Mono<Transaction> transactionMono = request.bodyToMono(Transaction.class);
        return transactionMono.flatMap( Request -> service.create(Request))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)));
    }

    /**
     * Search transactions in a range of dates with the format "yyyy-MM-dd HH:mm:ss".
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findTransactionsByRangeOfDates(ServerRequest request){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String identityNumber = request.pathVariable("identityNumber");

        LocalDateTime fromDate = LocalDateTime.parse(request.pathVariable("fromDate"), formatter);
        LocalDateTime toDate = LocalDateTime.parse(request.pathVariable("toDate"), formatter);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllByIdentityNumberAndDateOperationBetween(identityNumber
                        , fromDate, toDate), Transaction.class);
    }
}
