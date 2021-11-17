package com.bootcamp.debitcardoperations.services;

import com.bootcamp.debitcardoperations.documents.dto.CreditDTO;
import reactor.core.publisher.Mono;

public interface ICreditDTOService {
    /**
     * Update credit mono.
     *
     * @param credit the credit
     * @return the mono
     */
    Mono<CreditDTO> updateCredit(CreditDTO credit);

    /**
     * Find by id mono.
     *
     * @param contractNumber the contractNumber
     * @return the mono
     */
    Mono<CreditDTO> findCredit(String contractNumber);
}