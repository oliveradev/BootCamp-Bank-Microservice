package com.bootcamp.withdrawservice.handlers;

import com.bootcamp.withdrawservice.documents.dto.DebitAccountDTO;
import com.bootcamp.withdrawservice.documents.dto.TransactionCommand;
import com.bootcamp.withdrawservice.documents.entities.Withdraw;
import com.bootcamp.withdrawservice.services.IDebitAccountService;
import com.bootcamp.withdrawservice.services.ITransactionService;
import com.bootcamp.withdrawservice.services.IWithdrawService;
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
public class WithdrawHandler {
    private static final Logger log = LoggerFactory.getLogger(WithdrawHandler.class);
    @Autowired
    private IDebitAccountService accountService;

    @Autowired
    private IWithdrawService service;

    @Autowired
    private ITransactionService transactionService;

    public Mono<ServerResponse> createWithdraw(ServerRequest request){
        Mono<Withdraw> monoWithdraw = request.bodyToMono(Withdraw.class);

        return monoWithdraw
                .flatMap( withdrawRequest -> accountService.findByAccountNumber(withdrawRequest.getTypeOfAccount(), withdrawRequest.getAccountNumber())
                        .filter(account -> account.getAmount() >= withdrawRequest.getAmount())
                        .flatMap(account->{
                            //Validando que no esté en el límite de movimientos por mes
                            if(account.getMaxLimitMovementPerMonth()>=account.getMovementPerMonth()){
                                account.setMovementPerMonth(account.getMovementPerMonth()+1);
                                account.setAmount(account.getAmount()-withdrawRequest.getAmount());
                            }else if(account.getMaxLimitMovementPerMonth()< account.getMovementPerMonth()){
                                log.info("La comisión es de: " + account.getCommission());
                                account.setAmount(account.getAmount()-withdrawRequest.getAmount()- account.getCommission());
                            }
                            log.info("El ID del débito es" + account.getId());
                            return accountService.updateDebit(account.getTypeOfAccount(), account);
                        })
                        .flatMap(ope ->{
                            TransactionCommand transaction = new TransactionCommand();
                            transaction.setTypeOfAccount(ope.getTypeOfAccount());
                            transaction.setTypeoftransaction("WITHDRAW");
                            transaction.setCustomerIdentityNumber(ope.getCustomerIdentityNumber());
                            transaction.setTransactionAmount(withdrawRequest.getAmount());
                            transaction.setIdentityNumber(withdrawRequest.getAccountNumber());
                            return transactionService.saveTransaction(transaction);
                        })
                        .flatMap(trans -> accountService.findByAccountNumber(trans.getTypeOfAccount(), trans.getIdentityNumber()))
                        .flatMap(debit -> {
                            if(debit.getMaxLimitMovementPerMonth()<debit.getMovementPerMonth()){
                                TransactionCommand Commission = new TransactionCommand();
                                Commission.setTypeOfAccount(debit.getTypeOfAccount());
                                Commission.setTypeoftransaction("COMMISSION");
                                Commission.setCustomerIdentityNumber(debit.getCustomerIdentityNumber());
                                Commission.setTransactionAmount(debit.getCommission());
                                Commission.setIdentityNumber(withdrawRequest.getAccountNumber());
                                return transactionService.saveTransaction(Commission);
                            } else {
                                return Mono.just(DebitAccountDTO.builder().build());
                            }
                        })
                        .flatMap(withdraw -> service.create(withdrawRequest)))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Withdraw.class);
    }

    public Mono<ServerResponse> findWithdraw(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> deleteWithdraw(ServerRequest request){
        String id = request.pathVariable("id");

        Mono<Withdraw> monoWithdraw =service.findById(id);

        return monoWithdraw
                .doOnNext( c -> log.info("Se eliminará el retiro con Id: {}", c.getId()))
                .flatMap( c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateWithdraw(ServerRequest request){
        Mono<Withdraw> monoWithdraw = request.bodyToMono(Withdraw.class);
        String id = request.pathVariable("id");
        return service.findById(id).zipWith(monoWithdraw, (db,req) -> {
                    db.setAmount(req.getAmount());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),Withdraw.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }



}
