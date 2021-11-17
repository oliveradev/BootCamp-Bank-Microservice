package com.bootcamp.withdrawservice.services.Impl;

import com.bootcamp.withdrawservice.documents.dto.DebitAccountDTO;
import com.bootcamp.withdrawservice.services.IDebitAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DebitAccountDTOServiceImpl implements IDebitAccountService {

    private static final Logger log = LoggerFactory.getLogger(DebitAccountDTOServiceImpl.class);

    @Autowired
    private WebClient.Builder client;

    @Override
    public Mono<DebitAccountDTO> findByAccountNumber(String debitType, String accountNumber) {
        if(debitType.equals("SAVING_ACCOUNT")){
            return client.baseUrl("http://localhost:9002/api/savingAccount")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", accountNumber)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> log.info("Account Response: Account Amounth={}", c.getAmount()));
        }else if(debitType.equals("CURRENT_ACCOUNT")){
            return client.baseUrl("http://localhost:9004/api/savingAccount")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", accountNumber)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> log.info("Account Response: Account Amounth={}", c.getAmount()));
        }else if(debitType.equals("FIXEDTERM_ACCOUNT")){
            return client.baseUrl("http://localhost:9005/api/savingAccount")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", accountNumber)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> log.info("Account Response: Account Amounth={}", c.getAmount()));
        }else return Mono.empty();
    }

    @Override
    public Mono<DebitAccountDTO> updateDebit(String debitType, DebitAccountDTO account) {

        log.info("Update Debit Function");
        log.info("Debit Type: {}", debitType);

        if(debitType.equals("SAVING_ACCOUNT")){
            return client.baseUrl("http://localhost:9002/api/savingAccount")
                    .build()
                    .put()
                    .uri("/{id}", account.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(account)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else if(debitType.equals("CURRENT_ACCOUNT")){
            return client.baseUrl("http://localhost:9004/api/savingAccount")
                    .build()
                    .put()
                    .uri("/{id}", account.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(account)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else if(debitType.equals("FIXEDTERM_ACCOUNT")){
            return client.baseUrl("http://localhost:9005/api/savingAccount")
                    .build()
                    .put()
                    .uri("/{id}", account.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(account)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else return Mono.empty();

    }
}
