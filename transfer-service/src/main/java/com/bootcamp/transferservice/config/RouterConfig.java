package com.bootcamp.transferservice.config;

import com.bootcamp.transferservice.handlers.TransferHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(TransferHandler transferHandler){

        return route(GET("/api/transfer"), transferHandler::findAll)
                .andRoute(GET("/api/transfer/{id}"),transferHandler::findTransfer)
                .andRoute(POST("/api/transfer"), transferHandler::createTransfer)
                .andRoute(PUT("/api/transfer/{id}"), transferHandler::updateTransfer)
                .andRoute(DELETE("/api/transfer/{id}"), transferHandler::deleteTransfer);
    }
}
