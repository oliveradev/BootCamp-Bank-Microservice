package com.bootcamp.msConsumptions.services;

import com.bootcamp.msConsumptions.models.dto.CreditCardDTO;
import reactor.core.publisher.Mono;

/**
 * The interface Credit card dto service.
 */
public interface ICreditCardDTOService{
    /**
     * Update credit mono.
     *
     * @param credit the credit
     * @return the mono
     */
    public Mono<CreditCardDTO> updateCredit(CreditCardDTO credit);

    /**
     * Find by pan mono.
     *
     * @param pan the pan
     * @return the mono
     */
    public Mono<CreditCardDTO> findByPan(String pan);
}
