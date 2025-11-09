package com.webapi.employeeapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.webapi.employeeapi.dto.EmployeeDto;
import com.webapi.employeeapi.entity.Employee;
import com.webapi.employeeapi.exception.ResourceNotFoundException;
import com.webapi.employeeapi.repository.EmployeeRepository;
import com.webapi.employeeapi.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	private EmployeeDto mapToDto(Employee e) {
		return new EmployeeDto(e.getId(), e.getName(), e.getEmail(), e.getDepartment());
	}
	
	private Employee mapToEntity(EmployeeDto dto) {
		return Employee.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.department(dto.getDepartment())
				.build();
	}
	
	@Override
	public List<EmployeeDto> getAllEmployees() {
		return employeeRepository.findAll()
				.stream().map(this::mapToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public Page<EmployeeDto> getEmployees(Pageable pageable, String q){
		Specification<Employee> spec = (root, query, cb) -> {
			if (q == null || q.isBlank()) return null;
			String like = "%" + q.toLowerCase() + "%";
			return cb.or(
				cb.like(cb.lower(root.get("name")),like),
				cb.like(cb.lower(root.get("email")), like),
				cb.like(cb.lower(root.get("department")), like)
					);		
		};
		return employeeRepository.findAll(spec, pageable).map(this::mapToDto);
	}
	
	@Override
	public EmployeeDto getEmployeeById(Long id) {
		Employee emp = employeeRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		return mapToDto(emp);
		
	}


	@Override
	public EmployeeDto createEmployee(EmployeeDto employee) {
		Employee saved = employeeRepository.save(mapToEntity(employee));
		return mapToDto(saved);
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto employee) {
		Employee existing = employeeRepository.findById(id)
							.orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: " + id));
		existing.setName(employee.getName());
		existing.setEmail(employee.getEmail());
		existing.setDepartment(employee.getDepartment());
		
		return mapToDto(employeeRepository.save(existing));
	}

	@Override
	public void deleteEmployee(Long id) {
		Employee emp = employeeRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Employee not found with id " + id));
		employeeRepository.delete(emp);
		
	}

}
