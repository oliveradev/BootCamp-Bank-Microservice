package com.bootcamp.creditcardservice.services.Impl;

import com.bootcamp.creditcardservice.documents.entities.CreditCard;
import com.bootcamp.creditcardservice.repositories.CreditCardRepository;
import com.bootcamp.creditcardservice.util.CreditCardCreator;
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
class CreditCardServiceImplTest {

    @InjectMocks
    CreditCardServiceImpl creditCardServiceImpl;
    @Mock
    CreditCardRepository creditCardRepositoryMock;

    private final CreditCard creditCard = CreditCardCreator.createValidCreditCard();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(creditCardRepositoryMock.findByPan(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(creditCard));

        BDDMockito.when(creditCardRepositoryMock.findByCustomer_CustomerIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(creditCard));

        BDDMockito.when(creditCardRepositoryMock.save(ArgumentMatchers.any(CreditCard.class)))
                .thenReturn(Mono.just(creditCard));

        BDDMockito.when(creditCardRepositoryMock.findAll())
                .thenReturn(Flux.just(creditCard));

        BDDMockito.when(creditCardRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(creditCard));

        BDDMockito.when(creditCardRepositoryMock.delete(ArgumentMatchers.any(CreditCard.class)))
                .thenReturn(Mono.empty());
    }

    @Test
    public void findByPan() {
        StepVerifier.create(creditCardServiceImpl.findByPan("1111-2222-3333-5555"))
                .expectSubscription()
                .expectNext(creditCard)
                .verifyComplete();
    }

    @Test
    public void validateCustomerIdentityNumber() {
        StepVerifier.create(creditCardServiceImpl.validateCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(creditCard)
                .verifyComplete();
    }

    @Test
    public  void findByCustomerIdentityNumber() {
        StepVerifier.create(creditCardServiceImpl.findByCustomerIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(creditCard)
                .verifyComplete();
    }

    @Test
    public void create() {
        CreditCard creditCardToBeSaved = CreditCardCreator.createCreditCardToBeSaved();
        StepVerifier.create(creditCardServiceImpl.create(creditCardToBeSaved))
                .expectSubscription()
                .expectNext(creditCard)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        StepVerifier.create(creditCardServiceImpl.findAll())
                .expectSubscription()
                .expectNext(creditCard)
                .verifyComplete();
    }

    @Test
    public void findById() {
        StepVerifier.create(creditCardServiceImpl.findById("qsxwdc123678ewq"))
                .expectSubscription()
                .expectNext(creditCard)
                .verifyComplete();
    }

    @Test
    public void update() {
        StepVerifier.create(creditCardServiceImpl.update(creditCard))
                .expectSubscription()
                .expectNext(creditCard)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(creditCardServiceImpl.delete(creditCard))
                .expectSubscription()
                .verifyComplete();
    }
}