package com.bootcamp.creditcardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CreditcardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditcardServiceApplication.class, args);
	}

}
