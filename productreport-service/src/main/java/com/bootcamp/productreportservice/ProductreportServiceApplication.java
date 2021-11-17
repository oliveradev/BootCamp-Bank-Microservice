package com.bootcamp.productreportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductreportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductreportServiceApplication.class, args);
	}

}
