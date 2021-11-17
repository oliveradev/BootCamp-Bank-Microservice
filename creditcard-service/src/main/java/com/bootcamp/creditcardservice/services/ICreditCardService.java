package com.bootcamp.creditcardservice.services;

import com.bootcamp.creditcardservice.documents.dto.Customer;
import com.bootcamp.creditcardservice.documents.dto.CustomerDTO;
import com.bootcamp.creditcardservice.documents.entities.CreditCard;
import reactor.core.publisher.Mono;

public interface ICreditCardService extends ICrudService<CreditCard, String> {
    /**
     * Gets customer.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the customer
     */


    public Mono<Customer> getCustomer(String customerIdentityNumber);

    /**
     * Find by pan mono.
     *
     * @param pan the pan
     * @return the mono
     */
    public Mono<CreditCard> findByPan(String pan);

    /**
     * New pan mono.
     *
     * @param id          the id
     * @param customerDTO the customer dto
     * @return the mono
     */
    public Mono<CustomerDTO> newPan(String id, CustomerDTO customerDTO);

    /**
     * Validate customer identity number mono.
     *
     * @param customerIdentityNumber the customer identity number
     * @return the mono
     */
    Mono<CreditCard> validateCustomerIdentityNumber(String customerIdentityNumber);

    public Mono<CreditCard> findByCustomerIdentityNumber(String customerIdentityNumber);
}
