# 🔐 JWT Authentication System

A secure and scalable JWT Authentication System built with Spring Boot, Spring Security, PostgreSQL, and JWT (JSON Web Token).

## 🚀 Features

* User Registration
* User Login
* JWT Access Token Authentication
* Refresh Token Support
* Role-Based Authorization (USER / ADMIN)
* Password Encryption with BCrypt
* Spring Security Integration
* PostgreSQL Database
* Global Exception Handling
* Request Validation
* RESTful API Architecture

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* JWT (JSON Web Token)
* PostgreSQL
* Lombok
* Maven / Gradle

### Security

* Access Token
* Refresh Token
* BCrypt Password Hashing
* Stateless Authentication

---

## 📂 Project Structure

```text
src
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── util
```

---

## ⚙️ Installation

### 1. Clone Repository

```bash
git clone https://github.com/IncridableAcuman/jwt-authentication.git
cd jwt-authentication
```

### 2. Configure Database

Update your `application.yml` or `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jwt_auth
spring.datasource.username=postgres
spring.datasource.password=password
```

### 3. Configure JWT Secret

```properties
jwt.secret=your-secret-key
jwt.access-expiration=900000
jwt.refresh-expiration=604800000
```

### 4. Run Application

#### Maven

```bash
mvn spring-boot:run
```

#### Gradle

```bash
./gradlew bootRun
```

---

## 🔑 Authentication Flow

### Registration

```http
POST /api/auth/register
```

Request:

```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "password123"
}
```

---

### Login

```http
POST /api/auth/login
```

Request:

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

Response:

```json
{
  "accessToken": "jwt-access-token",
  "refreshToken": "jwt-refresh-token"
}
```

---

### Refresh Token

```http
POST /api/auth/refresh
```

Request:

```json
{
  "refreshToken": "jwt-refresh-token"
}
```

---

## 🔒 Protected Endpoint Example

```http
GET /api/users/profile
```

Header:

```http
Authorization: Bearer <access_token>
```

---

## 📜 API Endpoints

| Method | Endpoint             | Description          |
| ------ | -------------------- | -------------------- |
| POST   | /api/auth/register   | Register User        |
| POST   | /api/auth/login      | Login User           |
| POST   | /api/auth/refresh    | Refresh Access Token |
| GET    | /api/users/profile   | Get User Profile     |
| GET    | /api/admin/dashboard | Admin Access         |

---

## 🧪 Testing

Run tests using:

```bash
mvn test
```

or

```bash
./gradlew test
```

---

## 🔐 Security Considerations

* Passwords are encrypted using BCrypt.
* Access Tokens are short-lived.
* Refresh Tokens are stored securely.
* Authentication is fully stateless.
* Spring Security protects secured endpoints.

---

## 📈 Future Improvements

* Email Verification
* Password Reset
* Two-Factor Authentication (2FA)
* OAuth2 (Google / GitHub)
* Docker Support
* Redis Token Blacklist

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss the proposed changes.

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

Developed by Izzat Abdusharipov
