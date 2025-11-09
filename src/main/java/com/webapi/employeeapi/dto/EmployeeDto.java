package com.webapi.employeeapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
	
	private Long id;
	
	@NotBlank(message = "name is required")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "email must be valid")
	private String email;
	
	@NotBlank(message = "department is required")
	private String department;

}
