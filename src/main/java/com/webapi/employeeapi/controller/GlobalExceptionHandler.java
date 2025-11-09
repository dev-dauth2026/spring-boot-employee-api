package com.webapi.employeeapi.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, Object> validation(MethodArgumentNotValidException ex) {
    Map<String, String> details = new LinkedHashMap<>();
    ex.getBindingResult().getFieldErrors()
      .forEach(fe -> details.put(fe.getField(), fe.getDefaultMessage()));
    return Map.of("error","VALIDATION_ERROR","details",details);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, Object> notFound(EntityNotFoundException ex) {
    return Map.of("error","NOT_FOUND","message",ex.getMessage());
  }

  @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, Object> integrity(org.springframework.dao.DataIntegrityViolationException ex) {
    return Map.of("error","BAD_REQUEST","message","Data integrity violation (e.g., duplicate email).");
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> other(Exception ex) {
    return Map.of("error","INTERNAL_SERVER_ERROR","message",ex.getMessage());
  }
}