package com.bootcamp.msConsumptions.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Web client config.
 */
@Configuration
public class WebClientConfig {

    /**
     * Registrar web client web client . builder.
     *
     * @return the web client . builder
     */
    @Bean
    @Qualifier("client")
    @LoadBalanced
    public WebClient.Builder registrarWebClient() {
        return WebClient.builder();
    }
}
