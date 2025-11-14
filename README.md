# Employee API (Spring Boot 3 + PostgreSQL)

A clean, real-world REST API built with **Spring Boot 3** and **PostgreSQL**, featuring layered architecture, DTO validation, pagination, search, audit timestamps, Swagger/OpenAPI documentation, and production‑grade security structure prepared with Spring Security + JWT (Keycloak‑ready).

## Tech Stack
| Layer | Technology |
|-------|-------------|
| Framework | Spring Boot 3.5.7 |
| Language | Java 21 |
| Database | PostgreSQL 14+ |
| ORM / JPA | Spring Data JPA + Hibernate 6 |
| Validation | Jakarta Validation |
| Documentation | SpringDoc OpenAPI 2.6.0 |
| Security (prepared) | Spring Security + OAuth2 Resource Server (JWT) |
| Build Tool | Maven |
| Utilities | Lombok |

## Architecture
```
Controller  →  Service  →  Repository  →  Database
        ↑
   DTOs + Validation + Global Exception Handling
```

## Features
- CRUD operations for Employees
- Pagination & searching by name/email/department
- Validation with `@Valid`
- REST‑friendly error responses
- Automatic audit timestamps (`createdAt`, `updatedAt`)
- DTO → Entity separation
- Swagger UI documentation
- PostgreSQL configuration
- Security structure ready for production using JWT (Keycloak compatible)

## Setup & Run

### Database Setup
```
CREATE DATABASE employee_db;
```

### Configure PostgreSQL
`src/main/resources/application.properties`

```
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### Run
```
mvn spring-boot:run
```

Or:
```
mvn clean package
java -jar target/employeeapi-0.0.1-SNAPSHOT.jar
```

## Swagger Documentation
- Swagger UI → http://localhost:8080/swagger-ui/index.html  
- OpenAPI JSON → http://localhost:8080/v3/api-docs  

### OpenAPI Global Metadata Example
```
@OpenAPIDefinition(
    info = @Info(
        title = "Employee API",
        version = "v1",
        description = "Spring Boot 3 REST API for managing employees"
    ),
    servers = @Server(url = "http://localhost:8080", description = "Local Development Server")
)
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/employees | Paginated list + search |
| GET | /api/employees/{id} | Get employee by ID |
| POST | /api/employees | Create employee |
| PUT | /api/employees/{id} | Update employee |
| DELETE | /api/employees/{id} | Delete employee |

### Example
POST `/api/employees`
```
{
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "department": "Engineering"
}
```

Response:
```
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "department": "Engineering",
  "createdAt": "2025-11-10T14:20:00Z",
  "updatedAt": "2025-11-10T14:20:00Z"
}
```

## Security (Configured for Production Use)

Security is implemented following real enterprise practice:

- Spring Boot as **OAuth2 Resource Server**
- JWT validation via `issuer-uri`
- Role-based access: Only ADMIN can manage employees
- Audience validation for tokens
- Keycloak-ready configuration (can run locally using Docker)
- CORS configuration externalized

Local example (disabled unless issuer-uri active):

```
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8081/realms/employee-api
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8081/realms/employee-api/protocol/openid-connect/certs
```

## Learning Highlights
- How to design a layered API architecture
- Pageable search with Spring Data
- Swagger/OpenAPI integration
- Real PostgreSQL integration
- Production-level security preparation
- Clean commit practices

## Next Steps (Optional Enhancements)
- Add Keycloak login flow
- CI/CD pipeline (GitHub Actions)
- Add integration tests (Testcontainers)
- Deploy API to AWS/Render/Railway
