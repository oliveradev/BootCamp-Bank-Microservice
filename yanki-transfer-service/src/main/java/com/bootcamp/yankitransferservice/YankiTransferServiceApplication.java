package com.bootcamp.yankitransferservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YankiTransferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YankiTransferServiceApplication.class, args);
	}

}
