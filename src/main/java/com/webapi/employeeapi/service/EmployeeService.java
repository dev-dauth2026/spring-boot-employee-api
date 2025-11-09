package com.webapi.employeeapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapi.employeeapi.dto.EmployeeDto;

public interface EmployeeService {
	
	List<EmployeeDto> getAllEmployees();
	Page<EmployeeDto> getEmployees(Pageable pageable, String q);
	EmployeeDto getEmployeeById(Long id);
	EmployeeDto createEmployee(EmployeeDto employee);
	EmployeeDto updateEmployee(Long id, EmployeeDto employee);
	void deleteEmployee(Long id);
	

}
