package com.bootcamp.yankiservice.handlers;

import com.bootcamp.yankiservice.documents.YankiAccount;
import com.bootcamp.yankiservice.services.IDebitCardDTOService;
import com.bootcamp.yankiservice.services.IYankiService;
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
public class YankiHandler {

    private static final Logger log = LoggerFactory.getLogger(YankiHandler.class);

    @Autowired
    private IDebitCardDTOService debitCardDTOService;

    @Autowired
    private IYankiService service;

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<YankiAccount> yankiMono = request.bodyToMono(YankiAccount.class);

        return yankiMono.flatMap(account -> {
            Mono<YankiAccount> existAccount = service.validateAccount(account);
            return existAccount.flatMap(c->{
                if(c.getId()!=null){
                    log.info("Account exist, owner is: ",c.getOwnerName());
                    return Mono.empty();
                }
                account.setAmount(0.00);
                return service.create(account);
            });
        })
                .flatMap(c-> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> findByPhoneNumber(ServerRequest request){

        String phoneNumber = request.pathVariable("id");
        return service.findByPhoneNumber(phoneNumber).flatMap(c-> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), YankiAccount.class);
    }



    public Mono<ServerResponse> updateAmount(ServerRequest request){
        Mono<YankiAccount> accountMono = request.bodyToMono(YankiAccount.class);
        String id = request.pathVariable("id");
        return service.findById(id).zipWith(accountMono, (db,req) -> {
                    db.setAmount(req.getAmount());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.update(c),YankiAccount.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<YankiAccount> accountMono = service.findById(id);
        return accountMono
                .doOnNext(c -> log.info("Delete Yanki Account", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addDebitCard(ServerRequest request){
        String pan = request.pathVariable("pan");
        String phoneNumber = request.pathVariable("phoneNumber");
        log.info("NUMERO DE TELEFONO: ", phoneNumber);
        return service.findByPhoneNumber(phoneNumber)
                .flatMap( yanki->
                    debitCardDTOService.getDebitCard(pan)
                            .flatMap(debitCardDTO ->{
                               yanki.setDebitCard(debitCardDTO);
                                return service.update(yanki);
                            })
                )
                .flatMap(c-> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());

        /*return debitCardDTOService
                .getDebitCard(pan)
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());*/
    }

}
