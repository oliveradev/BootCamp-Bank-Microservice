package com.bootcamp.savingservice.config;

import com.bootcamp.savingservice.handlers.SavingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {
    /**
     * Routes router function.
     *
     * @param accountHandler the account handler
     * @return the router function
     */
    @Bean
    public RouterFunction<ServerResponse> routes(SavingHandler accountHandler){
        return route(GET("/api/savingAccount"), accountHandler::findAll)
                .andRoute(GET("/api/savingAccount/{customerIdentityNumber}"),accountHandler::findByCustomerIdentityNumber)
                .andRoute(GET("/api/savingAccount/account/{accountNumber}"), accountHandler::findByAccountNumber)
                .andRoute(POST("/api/savingAccount"), accountHandler::newSavingAccount)
                .andRoute(PUT("/api/savingAccount/{id}"), accountHandler::updateSavingAccount)
                .andRoute(DELETE("/api/savingAccount/{id}"), accountHandler::deleteSavingAccount);
    }
}
