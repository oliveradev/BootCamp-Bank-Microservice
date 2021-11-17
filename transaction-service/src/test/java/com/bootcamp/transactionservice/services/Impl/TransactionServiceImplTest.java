package com.bootcamp.transactionservice.services.Impl;

import com.bootcamp.transactionservice.models.Transaction;
import com.bootcamp.transactionservice.repositories.TransactionRepository;
import com.bootcamp.transactionservice.util.TransactionCreator;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;
    @Mock
    TransactionRepository transactionRepositoryMock;
    private static Transaction transaction = TransactionCreator.createValidTransaction();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(transactionRepositoryMock.save(ArgumentMatchers.any(Transaction.class)))
                .thenReturn(Mono.just(transaction));

        BDDMockito.when(transactionRepositoryMock.findAll())
                .thenReturn(Flux.just(transaction));

        BDDMockito.when(transactionRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(transaction));

        BDDMockito.when(transactionRepositoryMock.delete(ArgumentMatchers.any(Transaction.class)))
                .thenReturn(Mono.empty());

        BDDMockito.when(transactionRepositoryMock
                        .findAllByIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Flux.just(transaction));

        BDDMockito.when(transactionRepositoryMock
                        .findFirst10ByIdentityNumberOrderByDateOperationDesc(ArgumentMatchers.anyString()))
                .thenReturn(Flux.just(transaction));

        BDDMockito.when(transactionRepositoryMock
                        .findAllByIdentityNumberAndDateOperationBetween(ArgumentMatchers.anyString(),
                                ArgumentMatchers.any(LocalDateTime.class), ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(Flux.just(transaction));
    }

    @Test
    void create() {
        Transaction accountToBeSaved = TransactionCreator.createTransactionToBeSaved();
        StepVerifier.create(transactionServiceImpl.create(accountToBeSaved))
                .expectSubscription()
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    void findAll() {
        StepVerifier.create(transactionServiceImpl.findAll())
                .expectSubscription()
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    void findById() {
        StepVerifier.create(transactionServiceImpl.findById("6181e4a21c171614ae5e864e"))
                .expectSubscription()
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    void delete() {
        StepVerifier.create(transactionServiceImpl.delete(transaction))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void update() {
        StepVerifier.create(transactionServiceImpl.update(transaction))
                .expectSubscription()
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    void findAllByIdentityNumber() {
        StepVerifier.create(transactionServiceImpl.findAllByIdentityNumber("77380599"))
                .expectSubscription()
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    void findFirst10ByIdentityNumberOrderByDateOperationDesc() {
        StepVerifier.create(transactionServiceImpl.findFirst10ByIdentityNumberOrderByDateOperationDesc("77380599"))
                .expectSubscription()
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    void findAllByIdentityNumberAndDateOperationBetween() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse("2021-10-01 00:00:00", formatter);
        LocalDateTime toDate = LocalDateTime.parse("2021-12-31 00:00:00", formatter);

        StepVerifier.create(transactionServiceImpl
                        .findAllByIdentityNumberAndDateOperationBetween("77380599", fromDate, toDate))
                .expectSubscription()
                .expectNext(transaction)
                .verifyComplete();
    }
}