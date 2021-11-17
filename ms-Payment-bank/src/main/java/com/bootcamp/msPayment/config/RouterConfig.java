package com.bootcamp.msPayment.config;

import com.bootcamp.msPayment.handler.PaymentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * The type Router config.
 */
@Configuration
public class RouterConfig {

    /**
     * Routes router function.
     *
     * @param paymentHandler the payment handler
     * @return the router function
     */
    @Bean
    public RouterFunction<ServerResponse> routes(PaymentHandler paymentHandler){

        return route(GET("/api/payment"), paymentHandler::findAll)
                .andRoute(GET("/api/payment/{id}"), paymentHandler::findPayment)
                .andRoute(POST("/api/payment"), paymentHandler::newPayment)
                .andRoute(PUT("/api/payment/{id}"), paymentHandler::updatePayment)
                .andRoute(DELETE("/api/payment/{id}"), paymentHandler::deletePayment);

    }
}
