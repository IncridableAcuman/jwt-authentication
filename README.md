# Professional Full Stack Authentication System
Professional authentication system built with Spring Boot and jwt authentication.

**Features:**
- JWT Authentication
- Email Verification
- Secure HTTP-only Cookie
- Role-Based Authorization

## Tech Stack
### Backend:
- Java 17
- Spring Boot
- Spring Security
- JWT 
- PostgreSQL
- Docker
- Gradle

## Authentication Flow
1. User login in with email and password
2. Backend validates credentials
3. Access token is returned
4. Refresh token stored in HTTP-only cookie

## Installation

#### Backend:
```text
cd backend
mvn clean install
mvn spring-boot:run
```
#### Docker
```text
docker compose up --build
```
