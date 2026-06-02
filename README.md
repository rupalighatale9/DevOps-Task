# User Management System

A production-ready User Management System built with Spring Boot 3.x, featuring JWT Authentication, Role-Based Access Control (RBAC), and RESTful APIs.

## Features

- **JWT Authentication & Authorization**: Secure token-based authentication
- **Role-Based Access Control (RBAC)**: Admin, Manager, and User roles with different permissions
- **Entity Relationships**: User-Role (Many-to-Many), Task-User (Many-to-One)
- **Database Integration**: H2 Database with JPA/Hibernate
- **Validation**: Bean Validation with custom password requirements
- **Exception Handling**: Global exception handler with standardized error responses
- **API Documentation**: Swagger/OpenAPI with JWT support
- **Testing**: Unit tests and integration tests with JUnit 5 and Mockito

## Technology Stack

- **Java**: 21
- **Spring Boot**: 3.2.0
- **Spring Security**: 6.x
- **Spring Data JPA**: with Hibernate
- **Database**: H2 (in-memory)
- **Build Tool**: Maven
- **JWT**: jjwt 0.12.3
- **Testing**: JUnit 5, Mockito, Spring Boot Test
- **API Documentation**: SpringDoc OpenAPI 2.3.0
- **Other**: Lombok, Validation API, BCrypt

## Project Structure

```
src/main/java/com/usermanagement/
├── config/
│   ├── DataSeeder.java
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
├── controller/
│   ├── AdminController.java
│   ├── AuthController.java
│   ├── ManagerController.java
│   └── UserController.java
├── dto/
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── RegisterRequest.java
│   ├── RoleRequest.java
│   ├── RoleResponse.java
│   ├── TaskRequest.java
│   ├── TaskResponse.java
│   ├── UserRequest.java
│   ├── UserResponse.java
│   └── UserUpdateRequest.java
├── entity/
│   ├── Role.java
│   ├── Task.java
│   └── User.java
├── exception/
│   ├── DuplicateUserException.java
│   ├── ErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   ├── InvalidCredentialsException.java
│   ├── RoleNotFoundException.java
│   ├── TaskNotFoundException.java
│   └── UserNotFoundException.java
├── repository/
│   ├── RoleRepository.java
│   ├── TaskRepository.java
│   └── UserRepository.java
├── security/
│   ├── filter/
│   │   └── JwtAuthenticationFilter.java
│   ├── jwt/
│   │   └── JwtService.java
│   └── userdetails/
│       ├── CustomUserDetails.java
│       └── CustomUserDetailsService.java
├── service/
│   ├── AuthService.java
│   ├── RoleService.java
│   ├── TaskService.java
│   ├── UserService.java
│   └── impl/
│       ├── AuthServiceImpl.java
│       ├── RoleServiceImpl.java
│       ├── TaskServiceImpl.java
│       └── UserServiceImpl.java
└── UserManagementApplication.java
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
```bash
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Default Users

The application automatically seeds the database with default users:

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@usermanagement.com | Admin@123 |
| Manager | manager@usermanagement.com | Manager@123 |
| User | john@usermanagement.com | User@123 |

## API Endpoints

### Authentication APIs (Public)

#### Register
```http
POST /api/auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@gmail.com",
  "password": "Password@123"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@gmail.com",
  "password": "Password@123"
}
```

Response:
```json
{
  "token": "jwt-token",
  "type": "Bearer",
  "roles": ["ROLE_USER"]
}
```

### Admin APIs (Requires ROLE_ADMIN)

#### User Management
- `POST /api/admin/users` - Create user
- `GET /api/admin/users/{id}` - Get user by ID
- `GET /api/admin/users` - Get all users
- `PUT /api/admin/users/{id}` - Update user
- `DELETE /api/admin/users/{id}` - Delete user

#### Role Management
- `POST /api/admin/roles` - Create role
- `GET /api/admin/roles/{id}` - Get role by ID
- `GET /api/admin/roles` - Get all roles
- `DELETE /api/admin/roles/{id}` - Delete role
- `POST /api/admin/users/{userId}/roles/{roleId}` - Assign role to user
- `DELETE /api/admin/users/{userId}/roles/{roleId}` - Remove role from user

### Manager APIs (Requires ROLE_MANAGER)

#### Task Management
- `POST /api/manager/tasks` - Create task
- `GET /api/manager/tasks/{id}` - Get task by ID
- `GET /api/manager/tasks` - Get all tasks
- `PUT /api/manager/tasks/{id}` - Update task
- `PATCH /api/manager/tasks/{id}/status?status=COMPLETED` - Update task status
- `DELETE /api/manager/tasks/{id}` - Delete task
- `PUT /api/manager/tasks/{taskId}/assign/{userId}` - Assign task to user

#### User View
- `GET /api/manager/users` - View all users

### User APIs (Requires Authentication)

- `GET /api/user/profile` - Get current user's profile
- `GET /api/user/tasks` - Get tasks assigned to current user

## API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui.html`

OpenAPI JSON is available at: `http://localhost:8080/v3/api-docs`

### Using JWT with Swagger

1. Click the "Authorize" button in Swagger UI
2. Enter your JWT token (obtained from `/api/auth/login`)
3. Click "Authorize" to authenticate

## H2 Database Console

The H2 database console is available at: `http://localhost:8080/h2-console`

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:userdb`
- Username: `sa`
- Password: (leave empty)

## Password Requirements

Passwords must meet the following criteria:
- Minimum 8 characters
- At least one uppercase letter
- At least one lowercase letter
- At least one number
- At least one special character (@$!%*?&)

## Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn verify
```

### Test Coverage
The project includes unit tests for the service layer and integration tests for controllers, targeting 80%+ coverage.

## Security Features

- **JWT Token Authentication**: Stateless authentication with JWT tokens
- **BCrypt Password Encryption**: Secure password hashing
- **Method-Level Security**: Role-based access control using `@PreAuthorize`
- **CORS Configuration**: Configured for cross-origin requests
- **CSRF Protection**: Disabled for stateless API architecture

## Architecture Decisions

### Layered Architecture
The application follows a clean layered architecture:
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Data access operations
- **Entity Layer**: Database models
- **DTO Layer**: Data transfer objects for API communication

### Separation of Concerns
- DTOs are used to avoid exposing entities directly
- Service interfaces and implementations are separated
- Custom exceptions for specific error scenarios
- Global exception handler for centralized error handling

### Security Implementation
- JWT filter intercepts requests and validates tokens
- Custom UserDetailsService loads user-specific data
- Password encoder for secure password storage
- Method-level security annotations for fine-grained access control

## Error Handling

The application uses a global exception handler that returns standardized error responses:

```json
{
  "timestamp": "2026-06-01T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found",
  "path": "/api/admin/users/1"
}
```

## License

This project is licensed under the Apache License 2.0.

## Author

User Management Team
