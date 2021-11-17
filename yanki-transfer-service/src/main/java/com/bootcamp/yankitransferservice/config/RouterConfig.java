package com.bootcamp.yankitransferservice.config;

import com.bootcamp.yankitransferservice.handlers.TransferHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(TransferHandler handler){
        return route(POST("/api/yanki-transfer"), handler::create)
                .andRoute(GET("/api/yanki-transfer"), handler::findAll)
                .andRoute(GET("/api/yanki-transfer/{phoneNumber}"), handler::findByMainPhoneNumber)
                .andRoute(DELETE("/api/yanki-account/{id}"),handler::delete);

    }
}
