package com.webapi.employeeapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapi.employeeapi.dto.EmployeeDto;
import com.webapi.employeeapi.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAll(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<EmployeeDto>> page(
			@PageableDefault(page = 0, size = 5, sort = "name") Pageable pageable, 
			@RequestParam(required = false) String q){
		
		// Cap the size to  avoid abuse (max 50)
		int safePage = Math.max(0, pageable.getPageNumber());
		int safeSize = Math.min(50, Math.max(1, pageable.getPageSize()));
		
		Pageable safePageable = PageRequest.of(safePage, safeSize, pageable.getSort());
		
		return ResponseEntity.ok(employeeService.getEmployees(safePageable, q));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable Long id){
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto dto){
		EmployeeDto created = employeeService.createEmployee(dto);
		
		//Build Location hearder: /api/employees/{id}
		java.net.URI location = java.net.URI.create("/api/employees/" + created.getId());
		
		// Return 201 Created with the new record body
		return ResponseEntity.created(location).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @Valid @RequestBody EmployeeDto dto){
		return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
