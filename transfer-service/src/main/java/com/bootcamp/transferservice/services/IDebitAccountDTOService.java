package com.bootcamp.transferservice.services;

import com.bootcamp.transferservice.documents.dto.DebitAccountDTO;
import reactor.core.publisher.Mono;

public interface IDebitAccountDTOService {

    public Mono<DebitAccountDTO> findByAccountNumber(String typeofdebit, String accountNumber);


    public Mono<DebitAccountDTO> updateDebit(String typeofdebit, DebitAccountDTO account);
}
