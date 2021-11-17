package com.bootcam.currentservice.services.Impl;

import com.bootcam.currentservice.models.entities.Account;
import com.bootcam.currentservice.repositories.AccountRepository;
import com.bootcam.currentservice.util.AccountCreator;
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
class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountServiceImpl;
    @Mock
    AccountRepository accountRepositoryMock;

    private final Account account = AccountCreator.createValidAccount();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(accountRepositoryMock.save(ArgumentMatchers.any(Account.class)))
                .thenReturn(Mono.just(account));

        BDDMockito.when(accountRepositoryMock.findAll())
                .thenReturn(Flux.just(account));

        BDDMockito.when(accountRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(account));

        BDDMockito.when(accountRepositoryMock.delete(ArgumentMatchers.any(Account.class)))
                .thenReturn(Mono.empty());

        BDDMockito.when(accountRepositoryMock.findByCustomerIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(account));

        BDDMockito.when(accountRepositoryMock.findAllByCustomerIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Flux.just(account));

        BDDMockito.when(accountRepositoryMock.findByAccountNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(account));
    }

    @Test
    void create() {
        Account accountToBeSaved = AccountCreator.createAccountToBeSaved();
        StepVerifier.create(accountServiceImpl.create(accountToBeSaved))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void findAll() {
        StepVerifier.create(accountServiceImpl.findAll())
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void findById() {
        StepVerifier.create(accountServiceImpl.findById("qw123b4i3382"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void update() {
        StepVerifier.create(accountServiceImpl.update(account))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void delete() {
        StepVerifier.create(accountServiceImpl.delete(account))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void validateCustomerIdentityNumber() {
        StepVerifier.create(accountServiceImpl.validateCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void findAllByCustomerIdentityNumber() {
        StepVerifier.create(accountServiceImpl.findAllByCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void findByAccountNumber() {
        StepVerifier.create(accountServiceImpl.findByAccountNumber("123456"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void findByCustomerIdentityNumber() {
        StepVerifier.create(accountServiceImpl.findByCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(account)
                .verifyComplete();
    }
}