package com.financialreport.financialreportmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {
		"org.handler",
		"com.financialreport.financialreportmicroservice"
})
public class FinancialReportMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialReportMicroserviceApplication.class, args);
	}

}
