package com.webapi.employeeapi.config;

import jakarta.servlet.http.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       org.springframework.security.core.AuthenticationException authException) throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    String body = """
      {"timestamp":"%s","status":401,"error":"Unauthorized","path":"%s"}
      """.formatted(java.time.OffsetDateTime.now(), request.getRequestURI());
    response.getWriter().write(body);
  }
}