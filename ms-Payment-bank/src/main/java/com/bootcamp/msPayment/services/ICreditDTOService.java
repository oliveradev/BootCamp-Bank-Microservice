package com.bootcamp.msPayment.services;

import com.bootcamp.msPayment.models.dto.CreditDTO;
import reactor.core.publisher.Mono;

/**
 * The interface Credit dto service.
 */
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
