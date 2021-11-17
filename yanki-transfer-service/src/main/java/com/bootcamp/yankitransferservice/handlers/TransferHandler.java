package com.bootcamp.yankitransferservice.handlers;

import com.bootcamp.yankitransferservice.documents.dto.TransactionDTO;
import com.bootcamp.yankitransferservice.documents.dto.YankiDTO;
import com.bootcamp.yankitransferservice.documents.entities.Transfer;
import com.bootcamp.yankitransferservice.services.IDebitCardDTOService;
import com.bootcamp.yankitransferservice.services.ITransactionService;
import com.bootcamp.yankitransferservice.services.ITransferService;
import com.bootcamp.yankitransferservice.services.IYankiService;
import com.mongodb.internal.connection.Server;
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
    private ITransactionService transactionService;

    @Autowired
    private ITransferService transferService;

    @Autowired
    private IYankiService yankiService;





    public Mono<ServerResponse> findByMainPhoneNumber(ServerRequest request){
        String phoneNumber = request.pathVariable("phoneNumber");
        return transferService.findByMainPhoneNumber(phoneNumber)
                .flatMap( c -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Transfer> accountMono = transferService.findById(id);
        return accountMono
                .doOnNext(c -> log.info("Delete Transfer", c.getId()))
                .flatMap(c -> transferService.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transferService.findAll(), Transfer.class);
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Transfer> transferMono = request.bodyToMono(Transfer.class);
        return transferMono.flatMap(transferRequest-> yankiService.findByPhoneNumber(transferRequest.getOriginPhoneNumber())
                .flatMap(originYanki->{
                    if(originYanki.getAmount()< transferRequest.getAmount()){
                        log.info("Origin Owner:",originYanki.getOwnerName());
                        log.info("Origin Amount:",originYanki.getAmount());
                        return Mono.error(new Throwable("THE AMOUNT OF TRANSFER EXCEDED THE AMOUNT OF ORIGIN ACCOUNT"));
                    }else{
                        return yankiService.findByPhoneNumber(transferRequest.getDestinationPhoneNumber())
                                .flatMap(destinationYanki -> {
                                    destinationYanki.setAmount(destinationYanki.getAmount()+transferRequest.getAmount());
                                    return yankiService.updateYanki(destinationYanki);
                                });
                    }
                })
                .flatMap(destinationYanki-> yankiService.findByPhoneNumber(transferRequest.getOriginPhoneNumber())
                        .flatMap(originYanki->{
                            originYanki.setAmount(originYanki.getAmount()- transferRequest.getAmount());
                            return yankiService.updateYanki(originYanki);
                        }))
                .flatMap(originYanki->{
                    TransactionDTO transaction = new TransactionDTO();
                    transaction.setAmountTransaction(transferRequest.getAmount());
                    transaction.setTypeTransaction("SEND");
                    transaction.setOriginPhoneNumber(transaction.getOriginPhoneNumber());
                    transaction.setDestinationPhoneNumber(transaction.getDestinationPhoneNumber());
                    transaction.setDescription(transferRequest.getDescription());
                    return transactionService.save(transaction);
                }).flatMap( transaction ->{
                            transferRequest.setMainPhoneNumber(transferRequest.getDestinationPhoneNumber());
                         return transferService.create(transferRequest);
                        })
                .flatMap(transaction -> {
                    transferRequest.setMainPhoneNumber(transferRequest.getOriginPhoneNumber());
                 return transferService.create(transferRequest);
                }))

                .flatMap(c-> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


}
