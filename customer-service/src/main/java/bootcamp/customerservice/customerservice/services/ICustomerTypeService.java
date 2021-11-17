package bootcamp.customerservice.customerservice.services;

import bootcamp.customerservice.customerservice.documents.CustomerType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerTypeService{
    /**
     * Find all flux.
     *
     * @return the flux
     */
    Flux<CustomerType> findAll();
    /**
     * Find by id mono.
     *
     * @param id the id
     * @return the mono
     */
    Mono<CustomerType> findById(String id);
    /**
     * Find by code mono.
     *
     * @param code the code
     * @return the mono
     */
    Mono<CustomerType> findByCode(String code);
    /**
     * Save mono.
     *
     * @param customerType the customer type
     * @return the mono
     */
    Mono<CustomerType> create(CustomerType customerType);

    /**
     * Delete mono.
     *
     * @param customer the customer
     * @return the mono
     */
    Mono<Void> delete(CustomerType customerType);
}
