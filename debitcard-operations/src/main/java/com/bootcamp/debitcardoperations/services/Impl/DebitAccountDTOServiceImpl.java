package com.bootcamp.debitcardoperations.services.Impl;

import com.bootcamp.debitcardoperations.documents.dto.DebitAccountDTO;
import com.bootcamp.debitcardoperations.services.IDebitAccountDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class DebitAccountDTOServiceImpl implements IDebitAccountDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebitAccountDTOServiceImpl.class);

    @Autowired
    @Qualifier
    private WebClient.Builder webClientBuilder;


    @Override
    public Mono<DebitAccountDTO> updateDebit(String typeofAccount, DebitAccountDTO debitAccountDTO) {
        LOGGER.info("initializing Debit Update");
        LOGGER.info("El tipo de debito es: " + typeofAccount);
        LOGGER.info("El id del débito es: " + debitAccountDTO.getId());
        if(typeofAccount.equals("SAVING_ACCOUNT")) {
            return webClientBuilder.baseUrl("http://localhost:9002/api/savingAccount")
                    .build()
                    .put()
                    .uri("/{id}", Collections.singletonMap("id", debitAccountDTO.getId()))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(debitAccountDTO)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else if(typeofAccount.equals("CURRENT_ACCOUNT")) {
            return webClientBuilder.baseUrl("http://localhost:9004/api/currentAccount")
                    .build()
                    .put()
                    .uri("/{id}", Collections.singletonMap("id", debitAccountDTO.getId()))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(debitAccountDTO)
                    .retrieve()
                    .bodyToMono(DebitAccountDTO.class);
        }else if(typeofAccount.equals("FIXEDTERM_ACCOUNT")) {
            return webClientBuilder.baseUrl("http://localhost:9005/api/fixedTermaAccount")
                    .build()
                    .put()
                    .uri("/{id}", Collections.singletonMap("id", debitAccountDTO.getId()))
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
        LOGGER.info("initializing Debit query: " + typeofAccount);
        params.put("accountNumber", accountNumber);
        if (typeofAccount.equals("SAVING_ACCOUNT")) {
            return webClientBuilder.baseUrl("http://SAVINGACCOUNT-SERVICE/api/savingAccount")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> LOGGER.info("Account Response: Account Amounth={}", c.getAmount()));
        }else if (typeofAccount.equals("CURRENT_ACCOUNT")) {
            return webClientBuilder.baseUrl("http://CURRENTACCOUNT-SERVICE/api/currentAccount")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> LOGGER.info("CreditCard Response: CreditCard Amounth={}", c.getAmount()));
        } else if (typeofAccount.equals("FIXEDTERM_ACCOUNT")) {
            return webClientBuilder.baseUrl("http://localhost:8096")
                    .build()
                    .get()
                    .uri("/account/{accountNumber}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(clientResponse -> clientResponse.bodyToMono(DebitAccountDTO.class))
                    .doOnNext(c -> LOGGER.info("CreditCard Response: CreditCard Amounth={}", c.getAmount()));
        } else {
            LOGGER.info("Entra aquí fallido");
            return Mono.empty();
        }
    }

}
