package com.bootcamp.msConsumptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The type Ms consumptions bank application.
 */
@SpringBootApplication
@EnableEurekaClient
public class MsConsumptionsBankApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		SpringApplication.run(MsConsumptionsBankApplication.class, args);
	}

}
