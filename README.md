# Task Management REST API

A RESTful API for task management built with Java and Spring Boot, featuring full CRUD operations, input validation, automated testing, and CI/CD pipeline.

## Tech Stack

- **Java 17** + **Spring Boot 3.5**
- **Spring Data JPA** with **H2 Database** (in-memory)
- **JUnit 5** + **Mockito** for testing
- **Swagger/OpenAPI** for API documentation
- **GitHub Actions** for CI/CD
- **Maven** for build management

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+

### Run the application
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

### Run tests
```bash
./mvnw test
```

## API Endpoints

| Method | Endpoint                    | Description         |
|--------|-----------------------------|---------------------|
| POST   | `/api/tasks`                | Create a new task   |
| GET    | `/api/tasks`                | Get all tasks       |
| GET    | `/api/tasks/{id}`           | Get task by ID      |
| PUT    | `/api/tasks/{id}`           | Update a task       |
| DELETE | `/api/tasks/{id}`           | Delete a task       |
| GET    | `/api/tasks/status/{status}`| Filter by status    |

## Request Body Example
```json
{
  "title": "Complete project documentation",
  "description": "Write README and API docs",
  "status": "TODO"
}
```

**Status options:** `TODO`, `IN_PROGRESS`, `DONE`

## API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui/index.html`

## Project Structure
```
src/main/java/com/paulomassao/task_management_api/
├── controller/     → REST API endpoints
├── service/        → Business logic
├── repository/     → Data access layer
├── entity/         → JPA entities
├── enums/          → Status enumerations
└── exception/      → Custom error handling
```

## Testing

The project includes **13 tests** covering:

- **Unit tests** for the service layer using Mockito
- **Integration tests** for the controller layer using MockMvc
- Input validation testing
- Error handling testing (404 Not Found, 400 Bad Request)

## CI/CD

GitHub Actions pipeline automatically runs on every push and pull request:

1. Builds the project
2. Runs all tests
3. Packages the application

## Author

**Paulo Massao** — Software Development student at SAIT (Calgary, AB)