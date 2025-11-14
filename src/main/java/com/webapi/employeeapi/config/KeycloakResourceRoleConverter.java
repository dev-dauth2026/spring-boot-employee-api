package com.webapi.employeeapi.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class KeycloakResourceRoleConverter implements Converter<Jwt, Collection<SimpleGrantedAuthority>> {
  private final String clientId;

  public KeycloakResourceRoleConverter(String clientId) {
    this.clientId = clientId;
  }

  @Override
  public Collection<SimpleGrantedAuthority> convert(Jwt jwt) {
    Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
    if (resourceAccess == null) return List.of();
    Map<String, Object> client = (Map<String, Object>) resourceAccess.get(clientId);
    if (client == null || client.get("roles") == null) return List.of();
    var roles = (Collection<String>) client.get("roles");
    return roles.stream()
      .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
      .collect(Collectors.toSet());
  }
}