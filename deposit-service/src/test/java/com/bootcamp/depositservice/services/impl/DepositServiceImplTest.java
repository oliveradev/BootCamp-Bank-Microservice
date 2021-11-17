package com.bootcamp.depositservice.services.impl;

import com.bootcamp.depositservice.models.Deposit;
import com.bootcamp.depositservice.repositories.DepositRepository;
import com.bootcamp.depositservice.util.DepositCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class DepositServiceImplTest {

    @InjectMocks
    DepositServiceImpl depositServiceImpl;
    @Mock
    DepositRepository depositRepositoryMock;

    private final Deposit deposit = DepositCreator.createValidDeposit();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(depositRepositoryMock.save(ArgumentMatchers.any(Deposit.class)))
                .thenReturn(Mono.just(deposit));

        BDDMockito.when(depositRepositoryMock.findAll())
                .thenReturn(Flux.just(deposit));

        BDDMockito.when(depositRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(deposit));

        BDDMockito.when(depositRepositoryMock.delete(ArgumentMatchers.any(Deposit.class)))
                .thenReturn(Mono.empty());
    }

    @Test
    public void create() {
        Deposit depositToBeSaved = DepositCreator.createDepositToBeSaved();
        StepVerifier.create(depositServiceImpl.create(depositToBeSaved))
                .expectSubscription()
                .expectNext(deposit)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        StepVerifier.create(depositServiceImpl.findAll())
                .expectSubscription()
                .expectNext(deposit)
                .verifyComplete();
    }

    @Test
    public void findById() {
        StepVerifier.create(depositServiceImpl.findById("qw123pou64yrte"))
                .expectSubscription()
                .expectNext(deposit)
                .verifyComplete();
    }

    @Test
    public void update() {
        StepVerifier.create(depositServiceImpl.update(deposit))
                .expectSubscription()
                .expectNext(deposit)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(depositServiceImpl.delete(deposit))
                .expectSubscription()
                .verifyComplete();
    }
}