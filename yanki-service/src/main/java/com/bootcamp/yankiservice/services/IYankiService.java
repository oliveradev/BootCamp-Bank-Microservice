package com.bootcamp.yankiservice.services;

import com.bootcamp.yankiservice.documents.YankiAccount;
import reactor.core.publisher.Mono;

public interface IYankiService extends ICrudService<YankiAccount, String> {
    Mono<YankiAccount> findByOwnerIdentityNumber(String ownerIdentityNumber);
    Mono<YankiAccount> findByPhoneNumber(String phoneNumber);
    Mono<YankiAccount> findByImei(String imei);
    Mono<YankiAccount> findByEmail(String email);
    Mono<YankiAccount> validateAccount(YankiAccount account);
}
