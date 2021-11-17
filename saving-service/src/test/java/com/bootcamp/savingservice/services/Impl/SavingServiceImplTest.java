package com.bootcamp.savingservice.services.Impl;

import com.bootcamp.savingservice.documents.entities.Account;
import com.bootcamp.savingservice.repositories.SavingRepository;
import com.bootcamp.savingservice.util.AccountCreator;
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
class SavingServiceImplTest {

    @InjectMocks
    SavingServiceImpl savingServiceImpl;
    @Mock
    SavingRepository savingRepositoryMock;

    private final Account account = AccountCreator.createValidAccount();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(savingRepositoryMock.save(ArgumentMatchers.any(Account.class)))
                .thenReturn(Mono.just(account));

        BDDMockito.when(savingRepositoryMock.findAll())
                .thenReturn(Flux.just(account));

        BDDMockito.when(savingRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(account));

        BDDMockito.when(savingRepositoryMock.delete(ArgumentMatchers.any(Account.class)))
                .thenReturn(Mono.empty());

        BDDMockito.when(savingRepositoryMock.findByCustomerIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(account));

        BDDMockito.when(savingRepositoryMock.findByAccountNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(account));
    }

    @Test
    void create() {
        Account accountToBeSaved = AccountCreator.createAccountToBeSaved();
        StepVerifier.create(savingServiceImpl.create(accountToBeSaved))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void findAll() {
        StepVerifier.create(savingServiceImpl.findAll())
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void findById() {
        StepVerifier.create(savingServiceImpl.findById("uiehfue8372"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    public void update() {
        StepVerifier.create(savingServiceImpl.update(account))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(savingServiceImpl.delete(account))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    public void findByCustomerIdentityNumber() {
        StepVerifier.create(savingServiceImpl.findByCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    public void validateCustomerIdentityNumber() {
        StepVerifier.create(savingServiceImpl.validateCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    public void findByAccountNumber() {
        StepVerifier.create(savingServiceImpl.findByAccountNumber("1111-2222"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }
}