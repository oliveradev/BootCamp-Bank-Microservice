package com.bootcamp.withdrawservice.services;

import com.bootcamp.withdrawservice.documents.dto.DebitAccountDTO;
import reactor.core.publisher.Mono;

public interface IDebitAccountService {
    Mono<DebitAccountDTO> findByAccountNumber(String debitType, String accountNumber);

    Mono<DebitAccountDTO> updateDebit(String debitType, DebitAccountDTO account);
}
