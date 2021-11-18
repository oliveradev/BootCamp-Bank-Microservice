package com.bootcamp.bootcoinservice.services;

import com.bootcamp.bootcoinservice.documents.entities.BootCoin;
import reactor.core.publisher.Mono;

public interface IBootCoinService extends ICrudService<BootCoin, String> {

    Mono<BootCoin> createBootCoin (BootCoin bootCoinDocument);

    Mono<BootCoin> updateBootCoin (String id, BootCoin bootCoinDocument);

    Mono<BootCoin> findByPhoneNumber(String phoneNumber);

    Mono<BootCoin> buyBootCoin(BootCoin bootCoin);

}
