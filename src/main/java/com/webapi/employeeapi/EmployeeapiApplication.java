package com.webapi.employeeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		  info = @Info(
		    title = "Employee API",
		    version = "v1",
		    description = "Spring Boot 3 REST API for managing employees with pagination, search, and validation"
		  ),
		  servers = {
				@Server(
					url = "http://localhost:8080",
					description = "Local Development Server"
					    )
					 }
		)
public class EmployeeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeapiApplication.class, args);
	}

}
