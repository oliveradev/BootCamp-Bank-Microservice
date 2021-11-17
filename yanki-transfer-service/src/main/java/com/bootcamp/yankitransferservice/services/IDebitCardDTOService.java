package com.bootcamp.yankitransferservice.services;

import com.bootcamp.yankitransferservice.documents.dto.DebitCardDTO;
import reactor.core.publisher.Mono;

public interface IDebitCardDTOService {
    Mono<DebitCardDTO> getDebitCard(String pan);
}
