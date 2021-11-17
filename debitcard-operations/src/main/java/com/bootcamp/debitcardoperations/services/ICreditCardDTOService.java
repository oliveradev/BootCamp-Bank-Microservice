package com.bootcamp.debitcardoperations.services;

import com.bootcamp.debitcardoperations.documents.dto.CreditCardDTO;
import reactor.core.publisher.Mono;

public interface ICreditCardDTOService {
    /**
     * Update credit mono.
     *
     * @param credit the credit
     * @return the mono
     */
    Mono<CreditCardDTO> updateCredit(CreditCardDTO credit);

    /**
     * Find by pan mono.
     *
     * @param pan the pan
     * @return the mono
     */
    Mono<CreditCardDTO> findByPan(String pan);
}