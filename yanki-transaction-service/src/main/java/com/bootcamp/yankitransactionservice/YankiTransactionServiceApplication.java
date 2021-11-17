package com.bootcamp.yankitransactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YankiTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YankiTransactionServiceApplication.class, args);
	}

}
