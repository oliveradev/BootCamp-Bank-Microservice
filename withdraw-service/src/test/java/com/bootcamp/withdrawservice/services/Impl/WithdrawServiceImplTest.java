package com.bootcamp.withdrawservice.services.Impl;

import com.bootcamp.withdrawservice.documents.entities.Withdraw;
import com.bootcamp.withdrawservice.repositories.WithdrawRepository;
import com.bootcamp.withdrawservice.util.WithdrawCreator;
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
class WithdrawServiceImplTest {

    @InjectMocks
    WithdrawServiceImpl withdrawServiceImpl;

    @Mock
    WithdrawRepository withdrawRepositoryMock;

    private final Withdraw withdraw = WithdrawCreator.createValidWithdraw(); //has id

    @BeforeEach
    public void setUp(){
        BDDMockito.when(withdrawRepositoryMock.findAll())
                .thenReturn(Flux.just(withdraw));

        BDDMockito.when(withdrawRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(withdraw));

        BDDMockito.when(withdrawRepositoryMock.delete(ArgumentMatchers.any(Withdraw.class)))
                .thenReturn(Mono.empty());

        BDDMockito.when(withdrawRepositoryMock.save(ArgumentMatchers.any(Withdraw.class)))
                .thenReturn(Mono.just(withdraw));
    }

    @Test
    public void create() {
        Withdraw customerToBeSaved = WithdrawCreator.createWithdrawToBeSaved();
        StepVerifier.create(withdrawServiceImpl.create(customerToBeSaved))
                .expectSubscription()
                .expectNext(withdraw)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        StepVerifier.create(withdrawServiceImpl.findAll())
                .expectSubscription()
                .expectNext(withdraw)
                .verifyComplete();
    }

    @Test
    public void findById() {
        StepVerifier.create(withdrawServiceImpl.findById("617ee981e22159302f88c899"))
                .expectSubscription()
                .expectNext(withdraw)
                .verifyComplete();
    }

    @Test
    public void update() {
        StepVerifier.create(withdrawServiceImpl.update(withdraw))
                .expectSubscription()
                .expectNext(withdraw)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(withdrawServiceImpl.delete(withdraw))
                .expectSubscription()
                .verifyComplete();
    }
}