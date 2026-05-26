# Bajaj Finserv Campus Hiring REST API (Acropolis - May 2026)

A high-performance, robust, production-ready Spring Boot REST API built for the Bajaj Finserv Acropolis Campus Hiring API round. It categorises mixed arrays of data, runs comprehensive calculations, generates specific string sequences, and provides a health status dashboard.

---

## 🚀 Deployed Production Endpoints
- **POST API endpoint:** `https://bfl-api-java-harshit-goud-production.up.railway.app/bfhl`
- **GET Health check:** `https://bfl-api-java-harshit-goud-production.up.railway.app/health`

---

## 🛠️ Tech Stack & Features
- **Backend Framework:** Spring Boot 3.2.5 (Java 21/25 compatible)
- **Architecture:** Controller-Service-Repository pattern with clear DTO separation
- **Deployment Platform:** Railway (Dockerized deployment workflow)
- **Reliability:** Built-in validation, global exception handlers, and comprehensive MockMvc integration tests (JUnit 5)

---

## 📋 API Specifications

### 1. Health Status check
- **Method:** `GET`
- **Route:** `/health`
- **Response Format:**
```json
{
  "user_id": "harshit_goud_29092005",
  "email": "harshitgoud230779@acropolis.in",
  "roll_number": "0827IT231051",
  "status": "UP"
}
```

---

### 2. Main API Processing
- **Method:** `POST`
- **Route:** `/bfhl`
- **Request Format:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```
- **Response Format:**
```json
{
  "is_success": true,
  "user_id": "harshit_goud_29092005",
  "email": "harshitgoud230779@acropolis.in",
  "roll_number": "0827IT231051",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

#### Core Logic Details:
- **Sum:** Computes the mathematical sum of all numeric elements in the input.
- **Concat String:** Gathers all alphabetical characters from the input, reverses their sequence, and formats them with alternating capitalisation (index 0 is uppercase, index 1 is lowercase, and so forth).

---

## 💻 Local Setup & Build

### Prerequisites
- JDK 21 or later
- Maven 3.9+

### Build and Run Tests
To verify all JUnit 5 controller/service integration test cases:
```bash
mvn clean test
```

### Start Server Locally
To start the application on port `8080`:
```bash
mvn spring-boot:run
```
Once started, you can access the local instance at `http://localhost:8080`.
