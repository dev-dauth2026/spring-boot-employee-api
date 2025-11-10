# Employee API

A clean, real-world REST API built with Spring Boot 3 and PostgreSQL, featuring layered architecture, DTO validation, pagination, search, and interactive Swagger (OpenAPI 3.1) documentation.

## Tech Stack
| Layer | Technology |
|-------|-------------|
| Framework | Spring Boot 3.5.7 |
| Language | Java 21 |
| Database | PostgreSQL 14+ |
| ORM / JPA | Spring Data JPA + Hibernate 6 |
| Validation | Jakarta Validation (Bean Validation) |
| Documentation | SpringDoc OpenAPI 2.6.0 (Swagger UI) |
| Build Tool | Maven |
| Utilities | Lombok for boilerplate reduction |

## Architecture
```
Controller  →  Service  →  Repository  →  Database
        ↑
     DTOs + Validation
```

- Controller – Handles HTTP requests & responses  
- Service – Business logic and validation  
- Repository – Database operations (Spring Data JPA)  
- DTOs – Request/response payloads with validation rules  

## Features
- CRUD operations for Employees  
- Pagination + search by `name`, `email`, `department`  
- Automatic audit timestamps (`createdAt`, `updatedAt`)  
- Centralized validation errors (`@Valid` + `@ControllerAdvice`)  
- Swagger UI for live API exploration  
- Clean DTO mapping → Entity separation  
- Real PostgreSQL database configuration  

## Setup & Run

### Prerequisites
- Java 21 or later  
- Maven 3.9+  
- PostgreSQL 14+ (running locally)

### Database
Create a database named `employee_db`.

```sql
CREATE DATABASE employee_db;
```

(Optional) update username/password in  
`src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### Run the application
```bash
mvn spring-boot:run
```
Or build a JAR:
```bash
mvn clean package
java -jar target/employeeapi-0.0.1-SNAPSHOT.jar
```

### Access the API
| Type | URL |
|------|-----|
| Base API | http://localhost:8080/api/employees |
| Swagger UI | http://localhost:8080/swagger-ui/index.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |

## API Overview

| Method | Endpoint | Description |
|:--:|:--|:--|
| GET | /api/employees | Paginated list + optional search (`?q=...`) |
| GET | /api/employees/{id} | Get employee by ID |
| POST | /api/employees | Create a new employee |
| PUT | /api/employees/{id} | Update existing employee |
| DELETE | /api/employees/{id} | Delete an employee |

## Example Request

**POST** `/api/employees`
```json
{
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "department": "Engineering"
}
```

**Response 201 Created**
```json
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "department": "Engineering",
  "createdAt": "2025-11-10T14:20:00Z",
  "updatedAt": "2025-11-10T14:20:00Z"
}
```

## Learning Highlights
- Pagination & search with `Pageable` and `Specification`
- DTO validation using `@NotBlank`, `@Email`
- Swagger (OpenAPI) documentation via annotations
- Database integration with JPA & PostgreSQL
- Layered code organization for clean scalability

## Swagger Configuration Example
```java
@OpenAPIDefinition(
    info = @Info(
        title = "Employee API",
        version = "v1",
        description = "Spring Boot 3 REST API for managing employees"
    ),
    servers = @Server(url = "http://localhost:8080", description = "Local Development Server")
)
```


