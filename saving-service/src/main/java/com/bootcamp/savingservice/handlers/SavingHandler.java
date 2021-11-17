package com.bootcamp.savingservice.handlers;

import com.bootcamp.savingservice.documents.dto.CustomerCommand;
import com.bootcamp.savingservice.documents.dto.CustomerQuery;
import com.bootcamp.savingservice.documents.entities.Account;
import com.bootcamp.savingservice.services.ICreditService;
import com.bootcamp.savingservice.services.ICustomerRequestService;
import com.bootcamp.savingservice.services.ISavingService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class SavingHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SavingHandler.class);

    @Autowired
    private ICustomerRequestService customerService;

    @Autowired
    private ISavingService service;

    @Autowired
    private ICreditService creditService;
    /**
     * Find all mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Account.class);
    }

    /**
     * Find by account number mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findByAccountNumber(ServerRequest request) {
        String accountNumber = request.pathVariable("accountNumber");
        LOGGER.info("El AccountNumber es " + accountNumber);
        return service.findByAccountNumber(accountNumber).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    /**
     * New saving account mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> newSavingAccount(ServerRequest request){
        Mono<Account> accountMono = request.bodyToMono(Account.class);
        return accountMono
                .flatMap(accountCreate -> customerService.getCustomer(accountCreate.getCustomerIdentityNumber())
                        .flatMap(customer->{
                            accountCreate.setCustomer(CustomerCommand.builder()
                                    .name(customer.getName()).code(customer.getCustomerType().getCode())
                                    .customerIdentityNumber(customer.getCustomerIdentityNumber()).build());
                            accountCreate.setTypeOfAccount("SAVING_ACCOUNT");
                            accountCreate.setMaxLimitMovementPerMonth(accountCreate.getMaxLimitMovementPerMonth());
                            accountCreate.setMovementPerMonth(0);
                            return  creditService.validateDebtorCredit(accountCreate.getCustomerIdentityNumber())
                                    .flatMap(debtor->{
                                        if(debtor == true){
                                            return Mono.empty();
                                        }else return service.validateCustomerIdentityNumber(accountCreate.getCustomerIdentityNumber());
                                    })
                                    .flatMap(accountFound -> {
                                        if(accountFound.getCustomerIdentityNumber() != null){
                                            LOGGER.info("La cuenta encontrada es: " + accountFound.getCustomerIdentityNumber());
                                            return Mono.empty();
                                        }else{
                                            LOGGER.info("No se encontró la cuenta ");
                                            return service.create(accountCreate);
                                        }
                                    });
                        })
                )
                .flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                ).switchIfEmpty(ServerResponse.badRequest().build());
    }
    /**
     * Find by customer identity number mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request){
        String customerIdentityNumber = request.pathVariable("customerIdentityNumber");
        return  service.findByCustomerIdentityNumber(customerIdentityNumber)
                .flatMap(c -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Delete saving account mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> deleteSavingAccount(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<Account> accountMono = service.findById(id);
        return accountMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Update saving account mono.
     *
     * @param request the request
     * @return the mono
     */
    public Mono<ServerResponse> updateSavingAccount(ServerRequest request){
        Mono<Account> accountMono = request.bodyToMono(Account.class);
        String id = request.pathVariable("id");
        return service.findById(id).zipWith(accountMono, (db,req) -> {
                    db.setAmount(req.getAmount());
                    db.setMovementPerMonth((req.getMovementPerMonth()));
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.update(c),Account.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
