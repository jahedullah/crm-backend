# CRM Backend

A Spring Boot backend for managing cars and drivers, featuring JWT authentication, API versioning, and OpenAPI (Swagger) documentation.

---

## Requirements
- Java 21
- Maven 3.9+
- Docker (optional, for containerized runs)

---

## Running Locally (IntelliJ or any IDE)

1. **Clone the repository**
   ```sh
   git clone <repo-url>
   cd crm-backend
   ```
2. **Open in IntelliJ IDEA** (or your preferred IDE)
3. **Build the project** (Maven will auto-download dependencies)
4. **Run the main class:**
   - `com.relatiq.crm_backend.CrmBackendApplication`

The server will start on [http://localhost:8080](http://localhost:8080)

---

## Running with Docker

1. **Build the Docker image:**
   ```sh
   docker build -t crm-backend .
   ```
2. **Run the container (exposing port 8080):**
   ```sh
   docker run -p 8080:8080 crm-backend
   ```

---

## API Documentation (Swagger UI)

Once running, access the API docs at:
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

The OpenAPI spec is available at:
- [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

---

## API Endpoints & Authentication
- All endpoints are versioned under `/api/v1/`
- Example: `POST /api/v1/auth/login`, `GET /api/v1/cars/filter`, etc.
- **Default user:**
  - Username: `admin`
  - Password: `admin`
- Use the `/api/v1/auth/login` endpoint to obtain a JWT token. Use this token as the `Authorization` header (e.g., `Bearer <token>`) for all subsequent requests.

---

## Notes
- Default port: **8080**
- Uses JWT for authentication (see Swagger UI for login and token usage)
- Springdoc OpenAPI 2.x for docs

---

## Troubleshooting
- If you see Swagger UI errors, ensure you have only `springdoc-openapi-starter-webmvc-ui` in your dependencies and are using a compatible Spring Boot version (3.x).
- For Docker, ensure port 8080 is free or change the mapping.