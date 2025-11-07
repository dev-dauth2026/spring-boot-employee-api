# Employee API (Spring Boot 3 + PostgreSQL)
- Real database: PostgreSQL (local)
- Clean layers: Controller → Service → Repository → DB
- DTO validation, pageable search, global error handling, Swagger

## Run
1) Ensure Postgres running, DB created (employee_db), user/password set.
2) `mvn spring-boot:run`
3) Swagger: http://localhost:8080/swagger-ui/index.html