package com.employeemanagement.employeemanagementmicroservice;

import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {
        "org.handler",
        "com.employeemanagement.employeemanagementmicroservice"
})
public class EmployeeManagementMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementMicroserviceApplication.class, args);
    }
}
