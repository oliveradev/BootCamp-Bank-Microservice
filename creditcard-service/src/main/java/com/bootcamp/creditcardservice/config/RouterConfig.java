package com.bootcamp.creditcardservice.config;


import com.bootcamp.creditcardservice.documents.entities.CreditCard;
import com.bootcamp.creditcardservice.handlers.CreditCardHandler;
import com.bootcamp.creditcardservice.services.Impl.CreditCardServiceImpl;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterConfig {

    @RouterOperations({ @RouterOperation(path = "/api/creditcard", beanClass = CreditCardServiceImpl.class, beanMethod = "getAll"),
            @RouterOperation(path = "/api/creditcard/{id}", beanClass = CreditCardServiceImpl.class, beanMethod = "getById"),
            @RouterOperation(path = "/api/creditcard/{customerIdentityNumber}\"", beanClass = CreditCardServiceImpl.class, beanMethod = "save"),
            @RouterOperation(path = "/api/creditcard/{id}", beanClass = CreditCardServiceImpl.class, beanMethod = "delete") })
    @Bean
    public RouterFunction<ServerResponse> routes(CreditCardHandler creditCardHandler){
        return route(GET("/api/creditcard"), creditCardHandler::findAll)
                .andRoute(GET("/api/creditcard/payment/{pan}"), creditCardHandler::findCreditCardByPan)
                .andRoute(GET("/api/creditcard/{id}"), creditCardHandler::findCreditCard)
                .andRoute(GET("/api/creditcard/customer/{customerIdentityNumber}"), creditCardHandler::findCreditCardByCustomer)
                .andRoute(RequestPredicates.POST("/api/creditcard/{customerIdentityNumber}"), creditCardHandler::newCreditCard)
                .andRoute(RequestPredicates.PUT("/api/creditcard/{id}"), creditCardHandler::updateCreditCard)
                .andRoute(RequestPredicates.DELETE("/api/creditcard/{id}"), creditCardHandler::deleteCreditCard);
    }


}
