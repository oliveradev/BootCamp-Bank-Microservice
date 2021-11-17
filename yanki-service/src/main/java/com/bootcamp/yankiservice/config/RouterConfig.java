package com.bootcamp.yankiservice.config;

import com.bootcamp.yankiservice.handlers.YankiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(YankiHandler handler){

        return route(GET("/api/yanki-account"), handler::findAll)
                .andRoute(POST("/api/yanki-account"), handler::create)
                .andRoute(PUT("/api/yanki-account/amount/{id}"),handler::updateAmount)
                .andRoute(DELETE("/api/yanki-account/{id}"),handler::delete)
                .andRoute(GET("/api/yanki-account/phone/{id}"), handler::findByPhoneNumber)
                .andRoute(POST("/api/yanki-account/{phoneNumber}/addDebitCard/{pan}"), handler::addDebitCard);
    }
}
