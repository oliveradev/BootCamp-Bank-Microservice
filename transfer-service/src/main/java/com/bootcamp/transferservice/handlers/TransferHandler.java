package com.bootcamp.transferservice.handlers;

import com.bootcamp.transferservice.documents.dto.TransactionDTO;
import com.bootcamp.transferservice.documents.entities.Transfer;
import com.bootcamp.transferservice.services.IDebitAccountDTOService;
import com.bootcamp.transferservice.services.ITransactionDTOService;
import com.bootcamp.transferservice.services.ITransferService;
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
public class TransferHandler {
    private static final Logger log = LoggerFactory.getLogger(TransferHandler.class);

    @Autowired
    private IDebitAccountDTOService accountService;

    @Autowired
    private ITransferService service;

    @Autowired
    private ITransactionDTOService transactionService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Transfer.class);
    }

    public Mono<ServerResponse> findTransfer(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> createTransfer(ServerRequest request){

        Mono<Transfer> transferMono = request.bodyToMono(Transfer.class);
        return transferMono.flatMap(transferRequest -> accountService.findByAccountNumber(transferRequest.getOriginTypeOfAccount(),transferRequest.getOriginAccount())
                        .flatMap(originAccount -> {
                            if(originAccount.getAmount()<transferRequest.getAmount()){
                                return Mono.error(new Throwable("THE AMOUNT OF TRANSFER EXCEDED THE AMOUNT OF ORIGIN ACCOUNT"));
                            }else return accountService.findByAccountNumber(transferRequest.getDestinationTypeOfAccount(),transferRequest.getDestinationAccount())
                                    .flatMap(destinationAccount -> {
                                        destinationAccount.setAmount(destinationAccount.getAmount()+transferRequest.getAmount());
                                        return accountService.updateDebit(destinationAccount.getTypeOfAccount(),destinationAccount);
                                    });
                        })
                        .flatMap(destinationAccount -> accountService.findByAccountNumber(transferRequest.getOriginTypeOfAccount(),transferRequest.getOriginAccount())
                                .flatMap(originAccount -> {
                                    originAccount.setAmount(originAccount.getAmount()-transferRequest.getAmount());
                                    return accountService.updateDebit(originAccount.getTypeOfAccount(),originAccount);
                                }))
                        .flatMap(originAccount -> {
                            TransactionDTO transaction = new TransactionDTO();
                            transaction.setTypeOfAccount(transferRequest.getOriginTypeOfAccount());
                            transaction.setTypeoftransaction("TRANSFER");
                            transaction.setCustomerIdentityNumber(originAccount.getCustomerIdentityNumber());
                            transaction.setTransactionAmount(transferRequest.getAmount());
                            transaction.setIdentityNumber(transferRequest.getOriginAccount());
                            transaction.setDestination(transferRequest.getDestinationAccount());
                            return transactionService.saveTransaction(transaction);
                        })
                        .flatMap(transfer ->  service.create(transferRequest)))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteTransfer(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<Transfer> transferMono = service.findById(id);

        return transferMono
                .doOnNext(c -> log.info("delete Transfer: TransferID={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateTransfer(ServerRequest request){
        Mono<Transfer> transferMono = request.bodyToMono(Transfer.class);
        String id = request.pathVariable("id");
        return service.findById(id).zipWith(transferMono, (db,req) -> {
                    db.setAmount(req.getAmount());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),Transfer.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
