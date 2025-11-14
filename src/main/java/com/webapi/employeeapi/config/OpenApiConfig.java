package com.webapi.employeeapi.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(new Info().title("Employee API").version("v1"))
      .addSecurityItem(new SecurityRequirement().addList("oauth2", java.util.List.of("openid")))
      .components(new Components().addSecuritySchemes("oauth2", new SecurityScheme()
        .type(SecurityScheme.Type.OAUTH2)
        .flows(new OAuthFlows().authorizationCode(new OAuthFlow()
          .authorizationUrl("http://localhost:8081/realms/employee-api/protocol/openid-connect/auth")
          .tokenUrl("http://localhost:8081/realms/employee-api/protocol/openid-connect/token")
          .scopes(new Scopes().addString("openid", "OpenID scope"))))));
  }
}