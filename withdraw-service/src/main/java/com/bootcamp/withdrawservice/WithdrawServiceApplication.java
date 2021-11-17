package com.bootcamp.withdrawservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WithdrawServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithdrawServiceApplication.class, args);
	}

}
