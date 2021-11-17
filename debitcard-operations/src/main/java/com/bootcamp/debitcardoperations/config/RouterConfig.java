package com.bootcamp.debitcardoperations.config;

import com.bootcamp.debitcardoperations.handlers.DebitPaymentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(DebitPaymentHandler debitPaymentHandler){

        return route(GET("/api/debitPayment"), debitPaymentHandler::findAll)
                .andRoute(GET("/api/debitPayment/{id}"), debitPaymentHandler::findDebitPayment)
                .andRoute(POST("/api/debitPayment"), debitPaymentHandler::createDebitPayment)
                .andRoute(GET("/api/debitPayment/origin/{originAccount}"), debitPaymentHandler::findAllByOriginAccount)
                .andRoute(DELETE("/api/debitPayment/{id}"), debitPaymentHandler::deleteDebitPayment);
    }
}
