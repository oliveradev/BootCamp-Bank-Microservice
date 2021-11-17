package com.bootcamp.msPayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The type Ms payment bank application.
 */
@SpringBootApplication
@EnableEurekaClient
public class MsPaymentBankApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		SpringApplication.run(MsPaymentBankApplication.class, args);
	}

}
