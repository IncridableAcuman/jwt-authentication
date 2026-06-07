# Professional Full Stack Authentication System
Professional authentication system built with Spring Boot, React,Typescript and jwt authentication.

**Features:**
- JWT Authentication
- Email Verification
- Secure HTTP-only Cookie
- Role-Based Authorization

## Tech Stack
### Backend:
- Java 21
- Spring Boot
- Spring Security
- JWT 
- PostgreSQL
- Maven
- Docker
- Redis
- RabbitMQ

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
