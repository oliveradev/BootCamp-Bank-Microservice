package com.bootcamp.creditservice.services.Impl;

import com.bootcamp.creditservice.models.dto.Customer;
import com.bootcamp.creditservice.models.dto.CustomerDTO;
import com.bootcamp.creditservice.models.entities.Credit;
import com.bootcamp.creditservice.repositories.CreditRepository;
import com.bootcamp.creditservice.util.CreditCreator;
import com.bootcamp.creditservice.util.CustomerCreator;
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
class CreditServiceImplTest {
    @InjectMocks
    CreditServiceImpl creditServiceImpl;
    @Mock
    CreditRepository creditRepositoryMock;

    private final Credit credit = CreditCreator.createValidCredit();
    //private final Customer customer = CustomerCreator.createValidCostumer();

    @BeforeEach
    public void setUp(){

        BDDMockito.when(creditRepositoryMock.findByContractNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(credit));

        BDDMockito.when(creditRepositoryMock.findAllByCustomerIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Flux.just(credit));

        BDDMockito.when(creditRepositoryMock.findByCustomerIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(credit));

        BDDMockito.when(creditRepositoryMock.save(ArgumentMatchers.any(Credit.class)))
                .thenReturn(Mono.just(credit));

        BDDMockito.when(creditRepositoryMock.findAll())
                .thenReturn(Flux.just(credit));

        BDDMockito.when(creditRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(credit));

        BDDMockito.when(creditRepositoryMock.delete(ArgumentMatchers.any(Credit.class)))
                .thenReturn(Mono.empty());

    }

    @Test
    public void findByContractNumber() {
        StepVerifier.create(creditServiceImpl.findByContractNumber("5555-4444-3333"))
                .expectSubscription()
                .expectNext(credit)
                .verifyComplete();
    }

    @Test
    public void getCustomer() {
    }

    @Test
    public void findAllByCustomerIdentityNumber() {
        StepVerifier.create(creditServiceImpl.validateCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(credit)
                .verifyComplete();
    }

    @Test
    public void validateCustomerIdentityNumber() {
        StepVerifier.create(creditServiceImpl.validateCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(credit)
                .verifyComplete();
    }

    @Test
    public void create() {
        Credit creditToBeSaved = CreditCreator.createCreditToBeSaved();

        StepVerifier.create(creditServiceImpl.create(creditToBeSaved))
                .expectSubscription()
                .expectNext(credit)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        StepVerifier.create(creditServiceImpl.findAll())
                .expectSubscription()
                .expectNext(credit)
                .verifyComplete();
    }

    @Test
    public void findById() {
        StepVerifier.create(creditServiceImpl.findById("6184778d76d308377e482a85"))
                .expectSubscription()
                .expectNext(credit)
                .verifyComplete();
    }

    @Test
    public void update() {
        Credit creditToBeUpdated = CreditCreator.createValidCredit();

        StepVerifier.create(creditServiceImpl.update(creditToBeUpdated))
                .expectSubscription()
                .expectNext(credit)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(creditServiceImpl.delete(credit))
                .expectSubscription()
                .verifyComplete();
    }
}