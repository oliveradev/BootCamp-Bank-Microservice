package bootcamp.customerservice.customerservice.controllers;

import bootcamp.customerservice.customerservice.documents.CustomerType;
import bootcamp.customerservice.customerservice.services.Impl.CustomerTypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/customer")
public class CustomerTypeController {

    private static final Logger log = LoggerFactory.getLogger(CustomerTypeController.class);
    @Autowired
    CustomerTypeServiceImpl service;

    @GetMapping("/type")
    public Flux<CustomerType> getAllTypes(){
        return service.findAll();
    }

    @DeleteMapping("/type/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return service.findById(id)
                .flatMap(type->{
                   return service.delete(type);
                })
                .map(type -> ResponseEntity.noContent().build());
    }

    @PostMapping("/type")
    public Mono<ResponseEntity<CustomerType>> create(@RequestBody CustomerType type){
        return service.create(type)
                .map(typeCreate ->{
                    return ResponseEntity.created(URI.create("/api/customeres/type"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(typeCreate);
                });
    }

}
