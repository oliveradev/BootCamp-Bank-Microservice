package com.bootcamp.transferservice.services.Impl;

import com.bootcamp.transferservice.documents.dto.DebitAccountDTO;
import com.bootcamp.transferservice.services.IDebitAccountDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class DebitAccountDTOServiceImpl implements IDebitAccountDTOService {
    private static final Logger log = LoggerFactory.getLogger(DebitAccountDTOServiceImpl.class);

    @Autowired
    private WebClient.Builder client;

    @Override
    public Mono<DebitAccountDTO> updateDebit(String typeofAccount, DebitAccountDTO debitAccountDTO) {
        log.info("initializing Debit Update");
        log.info("El tipo de debito es: " + typeofAccount);
        log.info("El id del débito es: " + debitAccountDTO.getId());
        if(typeofAccount.equals("SAVING_ACCOUNT")) {
            return client.baseUrl("http://localhost:9002/api/savingAccount")
                    .build()
                    .put()
                    .uri("/{id}", debitAccountDTO.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(debitAccountDTO)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else if(typeofAccount.equals("CURRENT_ACCOUNT")) {
            return client.baseUrl("http://localhost:9004/api/currentAccount")
                    .build()
                    .put()
                    .uri("/{id}",debitAccountDTO.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(debitAccountDTO)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else if(typeofAccount.equals("FIXEDTERM_ACCOUNT")) {
            return client.baseUrl("http://localhost:9005/api/fixedTermAccound")
                    .build()
                    .put()
                    .uri("/{id}",debitAccountDTO.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(debitAccountDTO)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else return Mono.empty();
    }



    @Override
    public Mono<DebitAccountDTO> findByAccountNumber(String typeofAccount, String accountNumber) {
        Map<String, Object> params = new HashMap<String, Object>();
        log.info("initializing Debit query: " + typeofAccount);
        params.put("accountNumber", accountNumber);
        if (typeofAccount.equals("SAVING_ACCOUNT")) {
            return client.baseUrl("http://localhost:9002/api/savingAccount")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> log.info("SavingAccount Response: Account Amounth={}", c.getAmount()));
        }else if (typeofAccount.equals("CURRENT_ACCOUNT")) {
            return client.baseUrl("http://localhost:9004/api/currentAccount")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> log.info("CurrentAccount Response: Account Amounth={}", c.getAmount()));
        } else if (typeofAccount.equals("FIXEDTERM_ACCOUNT")) {
            return client.baseUrl("http://localhost:9005/api/fixedTermAccound")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> log.info("FixedTermAccount Response: Account Amounth={}", c.getAmount()));
        } else {
            log.info("Entra aquí fallido");
            return Mono.empty();
        }
    }
}
