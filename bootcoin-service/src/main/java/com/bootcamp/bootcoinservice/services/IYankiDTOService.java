package com.bootcamp.bootcoinservice.services;

import com.bootcamp.bootcoinservice.documents.dto.YankiDTO;
import reactor.core.publisher.Mono;

public interface IYankiDTOService {
    Mono<YankiDTO> findByPhoneNumber(String customerIdentityNumber);

    Mono<YankiDTO> updateYanki(YankiDTO yankiDocument);
}
