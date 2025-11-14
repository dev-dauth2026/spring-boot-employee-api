package com.webapi.employeeapi.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<SimpleGrantedAuthority>> {
  @Override
  public Collection<SimpleGrantedAuthority> convert(Jwt jwt) {
    Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
    if (realmAccess == null || realmAccess.get("roles") == null) return List.of();
    var roles = (Collection<String>) realmAccess.get("roles");
    return roles.stream()
      .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
      .collect(Collectors.toSet());
  }
}