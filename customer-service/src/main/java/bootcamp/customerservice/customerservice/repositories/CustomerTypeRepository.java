package bootcamp.customerservice.customerservice.repositories;

import bootcamp.customerservice.customerservice.documents.CustomerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerTypeRepository extends ReactiveMongoRepository<CustomerType, String> {
    Mono<CustomerType> findByCode(String code);
}
