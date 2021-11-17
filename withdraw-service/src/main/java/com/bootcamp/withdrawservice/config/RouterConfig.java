package com.bootcamp.withdrawservice.config;

import com.bootcamp.withdrawservice.handlers.WithdrawHandler;
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
    public RouterFunction<ServerResponse> rutas(WithdrawHandler handler){
        return route(GET("/api/retire"), handler::findAll)
                .andRoute(GET("/api/retire/{id}"), handler::findWithdraw)
                .andRoute(DELETE("/api/retire/{id}"), handler::deleteWithdraw)
                .andRoute(PUT("/api/retire/{id}"), handler::updateWithdraw)
                .andRoute(POST("/api/retire"), handler::createWithdraw);
    }
}
