package com.bootcamp.productreportservice.config;

import com.bootcamp.productreportservice.handler.ProductReportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig{
    @Bean
    public RouterFunction<ServerResponse> routes(ProductReportHandler handler){
        return route(GET("/api/report/customer/{customerIdentityNumber}"), handler::generateReportCustomer);
    }

}
