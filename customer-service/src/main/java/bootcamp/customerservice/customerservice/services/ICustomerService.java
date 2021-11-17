package bootcamp.customerservice.customerservice.services;

import bootcamp.customerservice.customerservice.documents.Customer;
import org.springframework.web.reactive.function.server.EntityResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ICustomerService {
    /**
     * Find all flux.
     *
     * @return the flux
     */
    public Flux<Customer> findAll();

    /**
     * Find by id mono.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Customer> findById(String id);

    /**
     * Find by name mono.
     *
     * @param name the name
     * @return the mono
     */
    public Mono<Customer> findByName(String name);

    /**
     * Save mono.
     *
     * @param customer the customer
     * @return the mono
     */
    public Mono<Customer> save(Customer customer);

    /**
     * Update mono.
     *
     * @param id      the id
     * @param message the message
     * @return the mono
     */
    public Mono<Customer> update(String id, Customer message);

    /**
     * Delete mono.
     *
     * @param customer the customer
     * @return the mono
     */
    public Mono<Void> delete(Customer customer);

    /**
     * Find by customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    public Mono<Customer> findByCustomerIdentityNumber(String customerIdentityNumber);

    /**
     * Update card mono.
     *
     * @param id       the id
     * @param customer the customer
     * @return the mono
     */
    public Mono<Customer> updateCard(String id, Customer customer);
}
