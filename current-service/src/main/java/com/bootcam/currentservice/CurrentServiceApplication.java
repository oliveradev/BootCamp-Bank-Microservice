package com.bootcam.currentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CurrentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrentServiceApplication.class, args);
	}

}
