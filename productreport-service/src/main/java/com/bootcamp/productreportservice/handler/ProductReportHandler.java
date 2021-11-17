package com.bootcamp.productreportservice.handler;


import com.bootcamp.productreportservice.documents.dto.CustomerDTO;
import com.bootcamp.productreportservice.documents.entities.Productreport;
import com.bootcamp.productreportservice.services.IProductReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class ProductReportHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReportHandler.class);

    @Autowired
    private IProductReportService service;


    public Mono<ServerResponse> generateReportCustomer(ServerRequest request) {
        Productreport report = new Productreport();
        String customerIdentityNumber = request.pathVariable("customerIdentityNumber");
        return Mono.just(report)
                .flatMap(productreport -> service.getCustomer(customerIdentityNumber)
                        .flatMap(customerRequest -> {
                            productreport.setCustomer(
                                    CustomerDTO
                                            .builder()
                                            .name(customerRequest.getName())
                                            .customerIdentityNumber(customerRequest.getCustomerIdentityNumber())
                                            .code(customerRequest.getCustomerType().getCode())
                                            .build());
                            return service.getSavingAccount(customerIdentityNumber);
                        })
                        .flatMap(savingAccount -> {
                            if(!savingAccount.equals(null)){
                                productreport.getProductos().add(savingAccount);
                            }
                            return service.getFixedTermAccount(customerIdentityNumber);
                        })
                        .flatMap(fixedTermAccount -> {
                            if(!fixedTermAccount.equals(null)){
                                productreport.getProductos().add(fixedTermAccount);
                            }
                            return service.getCurrentAccount(customerIdentityNumber).collectList();
                        })
                        .flatMap(currentAccount -> {
                            if(currentAccount.get(0).getAccountNumber()!=null){
                                productreport.getProductos().add(currentAccount);
                            }
                            return service.getCreditCard(customerIdentityNumber);
                        })
                        .flatMap(creditcard -> {
                            if(!creditcard.getPan().equals(null)){
                                creditcard.setTypeOfAccount("CREDITCARD");
                                productreport.getProductos().add(creditcard);
                            }
                            return service.getCredit(customerIdentityNumber).collectList();
                        })
                        .flatMap(credit -> {
                            if(credit.get(0).getContractNumber().equals(null)){
                                credit.stream().forEach(c->{
                                   c.setTypeOfAccount("CREDIT");
                                });
                                productreport.getProductos().add(credit);
                            }
                            return Mono.just(productreport);
                        }))
                .flatMap(c-> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


}
