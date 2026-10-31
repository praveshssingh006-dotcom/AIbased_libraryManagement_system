# 📚 Library Management System -> [live](https://centrallibraryy.netlify.app)

A Library Management System built using **Java, Spring Boot, Spring Security, JWT, PostgreSQL, and Google Gemini AI**. The application provides REST APIs for managing books, user authentication, and book issuing operations, along with AI-powered assistance.

## 🚀 Features

- **Authentication & Authorization**
    - User registration and login.
    - JWT-based authentication.
    - Password encryption using BCrypt.
    - Role-based access control.

- **Book Management**
    - Add, update, and delete books.
    - Retrieve all books.
    - Get book details by ID.
    - Manage book availability and quantity.

- **Issue Record Management**
    - Issue books to users.
    - Maintain book issue records.
    - Track book availability and quantity.

- **AI Integration**
    - Google Gemini AI integration.
    - AI-powered assistance through REST APIs.

- **Security**
    - Spring Security.
    - JWT authentication filter.
    - CORS configuration.
    - Protected API endpoints.

- **Database**
    - PostgreSQL.
    - Spring Data JPA and Hibernate.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database Operations |
| Hibernate | ORM |
| PostgreSQL | Database |
| JWT | Authentication |
| BCrypt | Password Encryption |
| Google Gemini AI | AI Integration |
| Maven | Dependency Management |
| Docker | Containerization |
| Postman | API Testing |

## 📂 Project Structure

```text
LibraryManagement/
│
├── src/
│   ├── main/
│   │   ├── java/com/LibraryManagement/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── jwt/
│   │   │   ├── repo/
│   │   │   ├── service/
│   │   │   └── LibraryManagementApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.yaml
│   │
│   └── test/
│
├── .gitignore
├── .env.example
├── docker-compose.yml
├── pom.xml
└── README.md
```

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone YOUR_REPOSITORY_URL
cd LibraryManagement
```

### 2. Configure Environment Variables

Create a `.env` file in the project root or configure these environment variables in your system.

```env
DATASOURCE_URL=jdbc:postgresql://localhost:5432/lib_db
DATASOURCE_USER=your_database_username
DATASOURCE_PASSWORD=your_database_password

GEMINI_API_KEY=your_gemini_api_key

JWT_SECRET=your_secure_jwt_secret

FRONTEND_URL=http://localhost:5173
```

**Note:** Replace the placeholder values with your own credentials. Never commit your actual `.env` file to GitHub.

### 3. Start PostgreSQL Using Docker

Make sure Docker Desktop is running.

```bash
docker compose up -d
```

Verify the container:

```bash
docker ps
```

### 4. Run the Application

Using Maven Wrapper:

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**Linux / macOS:**

```bash
./mvnw spring-boot:run
```

Alternatively, build the JAR:

```bash
mvn clean package -DskipTests
```

Run the application:

```bash
java -jar target/*.jar
```

Backend URL:

```text
http://localhost:8080
```

## 🔐 Authentication

The application uses JWT-based authentication.

1. Register a new user.
2. Login using registered credentials.
3. Receive a JWT token.
4. Include the token in the Authorization header for protected endpoints.

Example:

```http
Authorization: Bearer YOUR_JWT_TOKEN
```

## 🧪 API Testing

APIs can be tested using Postman.

**Base URL:**

```text
http://localhost:8080
```

Main API modules:

- Authentication APIs
- Book Management APIs
- Issue Record APIs
- AI Assistance APIs

Refer to the controller classes for exact endpoint URLs, request formats, and response structures.

## 🐳 Docker Commands

Start PostgreSQL:

```bash
docker compose up -d
```

Stop PostgreSQL:

```bash
docker compose down
```

View PostgreSQL logs:

```bash
docker logs library_postgres
```

## 🔒 Environment Variables

The application uses environment variables to protect sensitive information.

| Variable | Description |
|---|---|
| DATASOURCE_URL | PostgreSQL JDBC URL |
| DATASOURCE_USER | Database username |
| DATASOURCE_PASSWORD | Database password |
| GEMINI_API_KEY | Google Gemini API key |
| JWT_SECRET | JWT signing secret |
| FRONTEND_URL | Frontend application URL |

See `.env.example` for the required configuration.

## 🔮 Future Improvements

- Book return management.
- Book reservation functionality.
- Pagination and sorting.
- Advanced search and filtering.
- Fine calculation for overdue books.
- Email notifications.
- AI-powered book recommendations.
- Automated testing.

## 👨‍💻 Author

**Vasco**

Computer Science & Engineering Student

Interested in Java, Spring Boot, Backend Development, and Software Engineering.

---

⭐ If you find this project useful, consider giving it a star!
