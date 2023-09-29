package com.financialreport.financialreportmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinancialReportMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialReportMicroserviceApplication.class, args);
	}

}
