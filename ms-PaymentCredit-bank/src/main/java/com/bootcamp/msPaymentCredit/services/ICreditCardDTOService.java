package com.bootcamp.msPaymentCredit.services;

import com.bootcamp.msPaymentCredit.models.dto.CreditCardDTO;
import reactor.core.publisher.Mono;

/**
 * The interface Credit card dto service.
 */
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
