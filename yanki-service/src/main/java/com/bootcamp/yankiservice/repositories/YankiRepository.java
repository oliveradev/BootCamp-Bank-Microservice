package com.bootcamp.yankiservice.repositories;

import com.bootcamp.yankiservice.documents.YankiAccount;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface YankiRepository extends ReactiveMongoRepository<YankiAccount, String> {
    @Query(value="{'ownerIdentityNumber': ?0 , 'phoneNumber': ?1, 'imei': ?2 ,'email': ?3 }")
    Mono<YankiAccount> validate(String ownerIdentityNumber, String phoneNumber, String imei, String email);
    Mono<YankiAccount> findByOwnerIdentityNumber(String ownerIdentityNumber);
    @Query("{'phoneNumber': ?0}")
    Mono<YankiAccount> findByPhoneNumber(String phoneNumber);
    Mono<YankiAccount> findByImei(String imei);
    Mono<YankiAccount> findByEmail(String email);
}
