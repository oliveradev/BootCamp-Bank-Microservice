package com.bootcamp.transferservice.services.Impl;

import com.bootcamp.transferservice.documents.entities.Transfer;
import com.bootcamp.transferservice.repositories.TransferRepository;
import com.bootcamp.transferservice.util.TransferCreator;
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
class TransferServiceImplTest {
    @InjectMocks
    TransferServiceImpl transferServiceImpl;

    @Mock
    TransferRepository transferRepositoryMock;

    private final Transfer transfer = TransferCreator.createValidTransfer();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(transferRepositoryMock.save(ArgumentMatchers.any(Transfer.class)))
                .thenReturn(Mono.just(transfer));

        BDDMockito.when(transferRepositoryMock.findAll())
                .thenReturn(Flux.just(transfer));

        BDDMockito.when(transferRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(transfer));

        BDDMockito.when(transferRepositoryMock.delete(ArgumentMatchers.any(Transfer.class)))
                .thenReturn(Mono.empty());
    }

    @Test
    public void create() {
        Transfer transferToBeSaved = TransferCreator.createTransferToBeSaved();
        StepVerifier.create(transferServiceImpl.create(transferToBeSaved))
                .expectSubscription()
                .expectNext(transfer)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        StepVerifier.create(transferServiceImpl.findAll())
                .expectSubscription()
                .expectNext(transfer)
                .verifyComplete();
    }

    @Test
    public void findById() {
        StepVerifier.create(transferServiceImpl.findById("617ee981e22159302f88c88w"))
                .expectSubscription()
                .expectNext(transfer)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(transferServiceImpl.delete(transfer))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    public void update() {
        StepVerifier.create(transferServiceImpl.update(transfer))
                .expectSubscription()
                .expectNext(transfer)
                .verifyComplete();
    }
}