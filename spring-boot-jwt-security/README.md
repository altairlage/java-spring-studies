
# SecureAPI
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

SecureAPI is a Java project developed using the Spring Boot framework. Its purpose is to provide a secure REST API by implementing authentication based on JSON Web Tokens (JWT). The project uses the H2 database for data persistence and Gradle as a build automation and dependency management tool.

## Technologies and Libraries Used

- **Java 22**: Programming language used for development.
- **Spring Boot 3**: Framework that simplifies the creation of standalone, production-ready Java applications.
- **Spring Security 6**: Framework that provides robust authentication and access control for Spring applications.
- **JSON Web Token (JWT)**: Open standard (RFC 7519) for creating access tokens that allow identity verification between different services.
- **H2 Database**: In-memory relational database used to facilitate development and testing.
- **Gradle**: Build automation and dependency management tool.

## Project Structure

The main structure of the project is as follows:

```
secureapi/
├── src/
│   ├── main/
│   │   ├── java/com/example/secureapi/
│   │   └── resources/
│   └── test/
├── build.gradle
└── secureapi.postman_collection.json
```

- **src/main/java/com/example/secureapi/**: Contains the application source code, including controllers, services, models, and configurations.
- **src/main/resources/**: Contains resources like configuration files (`application.properties`).
- **src/test/**: Contains unit and integration tests.
- **build.gradle**: Gradle script defining the dependencies and configurations for the project.
- **secureapi.postman_collection.json**: Postman collection with requests to test the API.

## Gradle Configuration

The `build.gradle` file defines the dependencies and plugins required for the project. Key configurations include:

```groovy
plugins {
    id 'org.springframework.boot' version '3.0.0'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
    id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
    runtimeOnly 'com.h2database:h2'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

- **Plugins**:
  - `org.springframework.boot`: Spring Boot plugin to simplify application setup and execution.
  - `io.spring.dependency-management`: Manages Spring dependency versions.
  - `java`: Support for Java projects.

- **Dependencies**:
  - `spring-boot-starter-security`: Provides libraries for Spring Security.
  - `spring-boot-starter-web`: Includes libraries for developing web applications, including RESTful APIs.
  - `jjwt-api`, `jjwt-impl`, `jjwt-jackson`: Libraries for creating and managing JWT tokens.
  - `h2`: Driver for the H2 database.
  - `spring-boot-starter-test`: Includes libraries for testing, such as JUnit.
  - `spring-security-test`: Provides support for security testing.

## Running the Project

To run the project, follow these steps:

1. **Clone the repository**:

   ```bash
   git clone https://github.com/altairlage/java-spring-studies.git
   cd java-spring-studies/spring-boot-jwt-security/secureapi
   ```

2. **Run the application using Gradle**:

   ```bash
   ./gradlew bootRun
   ```

   The application will be available at `http://localhost:8080`.

## Testing the API with Postman

The project includes a Postman collection (`secureapi.postman_collection.json`) containing requests to test the API. To use it:

1. **Import the collection in Postman**:
   - Open Postman.
   - Click "Import" and select the `secureapi.postman_collection.json` file.

2. **Authentication**:
   - Use the login request provided in the collection to obtain a JWT token.
   - After obtaining the token, configure Postman to add the JWT token to the headers of requests to protected endpoints.

3. **Testing Endpoints**:
   - Use the requests in the collection to interact with the API's endpoints.
   - Ensure the JWT token is correctly included in requests requiring authentication.

For more information on using Postman to test APIs with JWT authentication, refer to the [official documentation](https://learning.postman.com/docs/sending-requests/authorization/).


## REST Endpoints

The SecureAPI application exposes the following REST endpoints:

### Authentication Endpoints

1. **POST /auth/login**
   - **Description**: Authenticates a user and generates a JWT token.
   - **Request Body**:
     ```json
     {
       "username": "string",
       "password": "string"
     }
     ```
   - **Response**:
     ```json
     {
       "token": "string"
     }
     ```

### User Endpoints

2. **GET /users**
   - **Description**: Retrieves a list of all users. Requires authentication.
   - **Headers**:
     - `Authorization`: Bearer `<JWT Token>`
   - **Response**:
     ```json
     [
       {
         "id": "integer",
         "username": "string",
         "email": "string"
       }
     ]
     ```

3. **POST /users**
   - **Description**: Creates a new user. Requires authentication.
   - **Headers**:
     - `Authorization`: Bearer `<JWT Token>`
   - **Request Body**:
     ```json
     {
       "username": "string",
       "password": "string",
       "email": "string"
     }
     ```
   - **Response**:
     ```json
     {
       "id": "integer",
       "username": "string",
       "email": "string"
     }
     ```

4. **GET /users/{id}**
   - **Description**: Retrieves the details of a specific user by ID. Requires authentication.
   - **Headers**:
     - `Authorization`: Bearer `<JWT Token>`
   - **Response**:
     ```json
     {
       "id": "integer",
       "username": "string",
       "email": "string"
     }
     ```

5. **DELETE /users/{id}**
   - **Description**: Deletes a specific user by ID. Requires authentication.
   - **Headers**:
     - `Authorization`: Bearer `<JWT Token>`
   - **Response**:
     ```json
     {
       "message": "User deleted successfully"
     }
     ```

### Health Check Endpoint

6. **GET /health**
   - **Description**: Checks the health status of the application. Does not require authentication.
   - **Response**:
     ```json
     {
       "status": "UP"
     }
     ```

Each endpoint follows RESTful principles and requires proper headers and authentication tokens where specified.

## JWT Authentication with Spring Security

SecureAPI implements authentication using JSON Web Tokens (JWT) with the Spring Security framework. Here's an overview of how JWT authentication is configured and works in this project:

### Key Components

1. **Authentication Process**:
   - A user sends a POST request to the `/auth/login` endpoint with their username and password.
   - The application verifies the credentials using a custom implementation of the `UserDetailsService` interface.
   - If the credentials are valid, a JWT token is generated and returned to the user.

2. **Token Generation**:
   - The project uses the `io.jsonwebtoken` library to generate JWT tokens.
   - The token contains claims such as the username and expiration time, and it is signed with a secret key to ensure integrity.

3. **Token Validation**:
   - For secured endpoints, the incoming request must include the JWT token in the `Authorization` header (e.g., `Authorization: Bearer <token>`).
   - A custom filter intercepts the request, extracts the token, and validates it.
   - If valid, the user’s authentication is set in the security context, granting access to the endpoint.

4. **Spring Security Configuration**:
   - Spring Security is configured to secure specific endpoints and enable stateless sessions.
   - The security filter chain includes the custom JWT filter to handle authentication based on tokens.

### Example JWT Token Structure

A typical JWT token consists of three parts separated by dots (`.`):

1. **Header**:
   ```json
   {
     "alg": "HS256",
     "typ": "JWT"
   }
   ```

2. **Payload**:
   ```json
   {
     "sub": "username",
     "iat": 1516239022,
     "exp": 1516242622
   }
   ```

3. **Signature**:
   Generated using the header, payload, and a secret key.

### Security Configuration Code Snippet

Below is a simplified example of the security configuration:

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/login", "/health").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}
```

### Benefits of JWT Authentication

1. **Stateless**: No need to store session data on the server.
2. **Scalable**: Suitable for distributed systems where scalability is critical.
3. **Secure**: Ensures data integrity and provides mechanisms for expiration and claim validation.

For more details, refer to the official [Spring Security JWT documentation](https://spring.io/projects/spring-security).


## References

- [Spring Security JWT Tutorial | Toptal®](https://www.toptal.com/spring/spring-security-tutorial)
- [Protegendo sua API REST com Spring Security e autenticando usuários com token JWT em uma aplicação Spring Boot: um tutorial prático | medium](https://medium.com/@felipeacelinoo/protegendo-sua-api-rest-com-spring-security-e-autenticando-usu%C3%A1rios-com-token-jwt-em-uma-aplica%C3%A7%C3%A3o-d70e5b0331f9)
   - [spring-boot-jwt-security](https://github.com/lipeacelino/spring-boot-jwt-security)
- [Implement JWT authentication in a Spring Boot 3 application - Medium](https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac)
- [API authentication and authorization in Postman](https://learning.postman.com/docs/sending-requests/authorization/authorization/)
