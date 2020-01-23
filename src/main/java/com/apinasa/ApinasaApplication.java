package com.apinasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;

@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.apinasa"})
@SpringBootApplication
public class ApinasaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApinasaApplication.class, args);
	}

}
