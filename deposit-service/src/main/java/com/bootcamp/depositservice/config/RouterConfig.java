package com.bootcamp.depositservice.config;

import com.bootcamp.depositservice.handlers.DepositHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> rutas(DepositHandler handler) {
        return route(GET("/api/deposit"), handler::findAll)
                .andRoute(GET("/api/deposit/{id}"), handler::findDebit)
                .andRoute(DELETE("/api/deposit/{id}"), handler::deleteDebit)
                .andRoute(PUT("/api/deposit/{id}"), handler::updateDebit)
                .andRoute(POST("/api/deposit"), handler::createDeposit);
    }
}
