package com.webapi.employeeapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapi.employeeapi.dto.EmployeeDto;

public interface EmployeeService {
	
	Page<EmployeeDto> getEmployees(Pageable pageable, String q);
	EmployeeDto getEmployeeById(Long id);
	EmployeeDto createEmployee(EmployeeDto employee);
	EmployeeDto updateEmployee(Long id, EmployeeDto employee);
	void deleteEmployee(Long id);
	

}
