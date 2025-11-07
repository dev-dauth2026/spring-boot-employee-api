package com.webapi.employeeapi.service;

import java.util.List;

import com.webapi.employeeapi.dto.EmployeeDto;

public interface EmployeeService {
	
	List<EmployeeDto> getAllEmployees();
	EmployeeDto getEmployeeById(Long id);
	EmployeeDto createEmployee(EmployeeDto employee);
	EmployeeDto updateEmployee(Long id, EmployeeDto employee);
	void deleteEmployee(Long id);
	

}
