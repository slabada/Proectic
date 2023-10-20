package com.financemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {
		"org.handler",
		"com.financemicroservice"
})
public class FinanceMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceMicroserviceApplication.class, args);
	}

}
