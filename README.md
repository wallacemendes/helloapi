# Hello API - Spring Boot Learning Project

A **Spring Boot** API created as a first contact with the framework. This project explores fundamental concepts of building a REST API, including GET and POST endpoints, database integration, JWT-based authentication, and user roles management.

## Features

- **GET and POST endpoints** for interacting with the API.
- **Database connection** using JPA (Java Persistence API) to persist user data.
- **Authentication and Authorization**:
  - **JWT (JSON Web Token)** for securing endpoints and managing user sessions.
  - **Role-based access control** with `ADMIN` and `USER` roles.
- **Endpoints for user registration and login**.

## Technologies Used

- **Spring Boot**: Main framework for building the API.
- **JPA**: For database access and ORM (Object Relational Mapping).
- **JWT**: For secure token-based authentication.
- **Maven**: For dependency management and building the project.

## Endpoints

### Public Endpoints

- `POST /auth/register` - Register a new user.
- `POST /auth/login` - Authenticate a user and return a JWT token.

### Secured Endpoints (require JWT authentication)

- `GET /api/hellos` - Example of a simple GET endpoint, requires a valid token.
- `POST /api/hello` - Creates a new hello message, requires a valid token and ADMIN role.
  
#### **Request Body for `/api/hello`**:

```json
{
    "id": 22,
    "title": "Lucas4",
    "content": "Prazer em conhecer você!"
}
```

This endpoint allows you to create a new hello message with an `id`, `title`, and `content`. Authentication with a JWT token is required.

### How to Use the Secured Endpoints

1. **Authenticate** with the `/auth/login` endpoint to get a JWT token.
2. Use the token in the `Authorization` header to access secured endpoints, for example:

   ```http
   POST /api/hello
   Authorization: Bearer <your-token>
   Content-Type: application/json
   ```

3. **Example request**:

   ```json
   {
       "title": "someone's name",
       "content": "some custom message" (optional)
   }
   ```

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/hello-springboot-api.git
   cd hello-springboot-api
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

3. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`.

## Configuration

### JWT Secret

Set the JWT secret and expiration time in the `application.properties`:

```properties
app.jwtSecret=YourSecretKey
```

### Database Configuration

Configure your database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
```

## Testing the API

1. **Register a new user** by sending a `POST` request to `/auth/register` with a JSON body:

   ```json
   {
     "login": "user123",
     "password": "password123",
     "role": "USER"
   }
   ```

2. **Login** by sending a `POST` request to `/auth/login` with a JSON body:

   ```json
   {
     "login": "user123",
     "password": "password123"
   }
   ```

   This will return a JWT token that can be used for authentication.

3. **Create a hello message** by sending a `POST` request to `/api/hello` with a JSON body:

   ```json
   {
       "title": "someone's name",
       "content": "some custom message" (optional)
   }
   ```

4. Use the JWT token in the `Authorization` header for authenticated requests:

   ```http
   Authorization: Bearer <your-token>
   ```

## Future Improvements

- Implement more complex endpoints (e.g., update, delete).
- Add testing with JUnit and Mockito.
- Improve security with refresh tokens and password encryption.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to modify the content as needed!
