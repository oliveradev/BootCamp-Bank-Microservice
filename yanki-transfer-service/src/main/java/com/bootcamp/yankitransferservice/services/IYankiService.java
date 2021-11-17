package com.bootcamp.yankitransferservice.services;

import com.bootcamp.yankitransferservice.documents.dto.YankiDTO;
import reactor.core.publisher.Mono;

public interface IYankiService {
    Mono<YankiDTO> updateYanki(YankiDTO yanki);
    Mono<YankiDTO> findByPhoneNumber(String phoneNumber);
}
