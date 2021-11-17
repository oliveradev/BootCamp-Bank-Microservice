package bootcamp.customerservice.customerservice.services.Impl;

import bootcamp.customerservice.customerservice.documents.CustomerType;
import bootcamp.customerservice.customerservice.repositories.CustomerTypeRepository;
import bootcamp.customerservice.customerservice.services.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerTypeServiceImpl implements ICustomerTypeService {

    @Autowired
    private CustomerTypeRepository repository;

    @Override
    public Mono<CustomerType> create(CustomerType o) {
        return repository.save(o);
    }

    @Override
    public Flux<CustomerType> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<CustomerType> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> delete(CustomerType o) {
        return repository.delete(o);
    }

    @Override
    public Mono<CustomerType> findByCode(String code) {
        return repository.findByCode(code);
    }

}
