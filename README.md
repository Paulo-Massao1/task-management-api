# Task Management REST API

REST API for managing tasks, built with Spring Boot. Supports full CRUD operations with input validation, error handling, and filter by status.

I built this project to get hands-on experience with Spring Boot and practice setting up a CI/CD pipeline with GitHub Actions.

## How to run

You need Java 17+ installed.
```bash
./mvnw spring-boot:run
```

API runs at `http://localhost:8080`. You can also open `http://localhost:8080/swagger-ui/index.html` to explore and test the endpoints interactively.

## Endpoints
```
POST   /api/tasks                 → Create a task
GET    /api/tasks                 → List all tasks
GET    /api/tasks/{id}            → Get task by ID
PUT    /api/tasks/{id}            → Update a task
DELETE /api/tasks/{id}            → Delete a task
GET    /api/tasks/status/{status} → Filter by status (TODO, IN_PROGRESS, DONE)
```

Example request body:
```json
{
  "title": "Finish project docs",
  "description": "Write README and test the endpoints",
  "status": "TODO"
}
```

## Testing

13 tests covering the service and controller layers (unit + integration). Run them with:
```bash
./mvnw test
```

## Tech used

- Java 17, Spring Boot 3.5
- Spring Data JPA + H2 (in-memory database)
- JUnit 5 + Mockito
- Swagger/OpenAPI
- GitHub Actions for CI


## AI Disclosure

I used Claude (AI assistant) for guidance while building this project — learning Spring Boot concepts, structuring the code, and setting up the CI/CD pipeline. All code was written, tested, and understood by me.


## Author

Paulo Massao — Software Development student at SAIT, Calgary