package com.webapi.employeeapi.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name="Employee", description="Employee payload")
public class EmployeeDto {
	
	@Schema(example = "1")
	private Long id;
	
	@NotBlank(message = "name is required")
	@Schema(example = "Alice Nguyen")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "email must be valid")
	@Schema(example = "alice@example.com")
	private String email;
	
	@NotBlank(message = "department is required")
	@Schema(example = "Engineering")
	private String department;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Schema(type = "string", format = "date-time", example = "2025-11-10T12:34:56Z")
	 private Instant createdAt;

	 @JsonProperty(access = Access.READ_ONLY)
	 @Schema(type = "string", format = "date-time", example = "2025-11-10T12:35:56Z")
	 private Instant updatedAt;

}
