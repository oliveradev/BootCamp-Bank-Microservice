package bootcamp.customerservice.customerservice.services.Impl;

import bootcamp.customerservice.customerservice.documents.Customer;
import bootcamp.customerservice.customerservice.repositories.CustomerRepository;
import bootcamp.customerservice.customerservice.util.CustomerCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    CustomerServiceImpl customerServiceImpl;

    @Mock
    CustomerRepository customerRepositoryMock;

    private final Customer customer = CustomerCreator.createValidCustomer(); //has id

    @BeforeEach
    public void setUp(){
        BDDMockito.when(customerRepositoryMock.findAll())
                .thenReturn(Flux.just(customer));

        BDDMockito.when(customerRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(customer));

        BDDMockito.when(customerRepositoryMock.delete(ArgumentMatchers.any(Customer.class)))
                .thenReturn(Mono.empty());

        BDDMockito.when(customerRepositoryMock.save(ArgumentMatchers.any(Customer.class)))
                .thenReturn(Mono.just(customer));

        BDDMockito.when(customerRepositoryMock.save(CustomerCreator.createCustomerToBeUpdated()))
                .thenReturn(Mono.just(customer));

        BDDMockito.when(customerRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(customer));

        BDDMockito.when(customerRepositoryMock.findByCustomerIdentityNumber(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(customer));
    }

    @Test
    public void findAll(){
        StepVerifier.create(customerServiceImpl.findAll())
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void findById() {
        StepVerifier.create(customerServiceImpl.findById("617ee981e22159302f88c88a"))
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void delete() {
        StepVerifier.create(customerServiceImpl.delete(customer))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    public void save(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        StepVerifier.create(customerServiceImpl.save(customerToBeSaved))
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void update(){
        StepVerifier.create(customerServiceImpl
                .update("617ee981e22159302f88c88a", CustomerCreator.createCustomerToBeUpdated()))
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void findByName(){
        StepVerifier.create(customerServiceImpl.findByName("Tony Sanchez"))
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void findByCustomerIdentityNumber(){
        StepVerifier.create(customerServiceImpl.findByCustomerIdentityNumber("41526374"))
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    public void updateCard(){
        StepVerifier.create(customerServiceImpl.updateCard("617ee981e22159302f88c88a", customer))
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

}