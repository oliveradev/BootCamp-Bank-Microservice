package com.bootcamp.yankiservice.services;

import com.bootcamp.yankiservice.documents.dto.DebitCardDTO;
import reactor.core.publisher.Mono;

public interface IDebitCardDTOService {
    Mono<DebitCardDTO> getDebitCard(String pan);
}
