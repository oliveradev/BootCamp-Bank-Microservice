package bootcamp.customerservice.customerservice.services.Impl;

import bootcamp.customerservice.customerservice.documents.Customer;
import bootcamp.customerservice.customerservice.documents.CustomerType;
import bootcamp.customerservice.customerservice.repositories.CustomerTypeRepository;
import bootcamp.customerservice.customerservice.util.CustomerTypeCreator;
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
class CustomerTypeServiceImplTest {

    @InjectMocks
    CustomerTypeServiceImpl customerTypeServiceImpl;
    @Mock
    CustomerTypeRepository customerTypeRepositoryMock;

    private final CustomerType customerType = CustomerTypeCreator.createValidCustomerType();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(customerTypeRepositoryMock.findAll())
                .thenReturn(Flux.just(customerType));

        BDDMockito.when(customerTypeRepositoryMock.save(ArgumentMatchers.any(CustomerType.class)))
                .thenReturn(Mono.just(customerType));

        BDDMockito.when(customerTypeRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(customerType));

        BDDMockito.when(customerTypeRepositoryMock.findByCode(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(customerType));

        BDDMockito.when(customerTypeRepositoryMock.delete(ArgumentMatchers.any(CustomerType.class)))
                .thenReturn(Mono.empty());

    }

    @Test
    public void create() {
        CustomerType customerTypeToBeSaved = CustomerTypeCreator.createCustomerTypeToBeSaved();
        StepVerifier.create(customerTypeServiceImpl.create(customerTypeToBeSaved))
                .expectSubscription()
                .expectNext(customerType)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        StepVerifier.create(customerTypeServiceImpl.findAll())
                .expectSubscription()
                .expectNext(customerType)
                .verifyComplete();
    }

    @Test
    public void findById() {
        StepVerifier.create(customerTypeServiceImpl.findById("617ee25b3063e75966d09a8c"))
                .expectSubscription()
                .expectNext(customerType)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(customerTypeServiceImpl.delete(customerType))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    public void findByCode() {
        StepVerifier.create(customerTypeServiceImpl.findByCode("TP-04"))
                .expectSubscription()
                .expectNext(customerType)
                .verifyComplete();
    }
}