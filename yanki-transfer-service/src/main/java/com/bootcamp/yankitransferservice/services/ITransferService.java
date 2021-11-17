package com.bootcamp.yankitransferservice.services;

import com.bootcamp.yankitransferservice.documents.entities.Transfer;
import reactor.core.publisher.Mono;

public interface ITransferService extends ICrudService<Transfer, String> {
    Mono<Transfer> findByMainPhoneNumber(String phoneNumber);
}
