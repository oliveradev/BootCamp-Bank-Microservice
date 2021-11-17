package com.bootcamp.fixedtermservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FixedtermServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FixedtermServiceApplication.class, args);
	}

}
