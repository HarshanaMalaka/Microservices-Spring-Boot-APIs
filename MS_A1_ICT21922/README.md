# Student Management REST API

A Spring Boot REST API for managing student information with full CRUD operations, validation, and search functionality.

## 🚀 Technologies Used

- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **MySQL Database**
- **Maven**
- **Java 17**
- **Lombok**
- **ModelMapper**
- **Validation API**

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher
- Postman (for testing)

## 🛠️ Setup Instructions

### 1. Database Setup

#### Create MySQL Database
```sql
CREATE DATABASE studentDB;
```

### 2. Update Application Properties

- Edit src/main/resources/application.properties with your MySQL credentials:

```
spring.datasource.url=jdbc:mysql://localhost:3306/studentDB?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
```
### 3. Build the Application

```
mvn clean install
```

### 4. Run the Application

```
mvn spring-boot:run
```

## 📚 API Endpoints

### 1. Get All Students
- URL: GET /api/students
- Description: Retrieve all students from the database
- Response: 200 OK

### 2. Get Student by ID
- URL: GET /api/students/{id}
- Parameters: id (path parameter) - Student ID
- Success Response: 200 OK
- Error Response: 404 Not Found

### 3. Create Student
- URL: POST /api/students
- Body: Student object (ID is auto-generated)
- Success Response: 201 Created
- Error Response: 400 Bad Request (Validation errors)

### 4. Update Student
- URL: PUT /api/students/{id}
- Parameters: id (path parameter) - Student ID to update
- Success Response: 200 OK

### 5. Delete Student
- URL: DELETE /api/students/{id}
- Parameters: id (path parameter) - Student ID to delete
- Success Response: 204 No Content (No body)
- Error Response: 404 Not Found

### 6. Search Students
- URL: GET /api/students/search?keyword={keyword}
- Parameters: keyword (query parameter) - Search term
- Description: Search students by name or course (case-insensitive, partial match)
- Success Response: 200 OK

### 7. Get All Students with Pagination
- URL: GET /api/students/all
- Query Parameters
```text
page (optional, default: 0) - Page number (0-based)
size (optional, default: 10) - Number of items per page
sortBy (optional, default: id) - Field to sort by (id, name, email, course, age)
sortDirection (optional, default: asc) - Sort direction (asc or desc)
```

## 📋 Request/Response Format

### Student Object Schema:

```json
{
  "id": "number (auto-generated)",
  "name": "string (required)",
  "email": "string (required, valid email)",
  "course": "string (required)",
  "age": "number (required, min: 18)"
}
```
### Error Response Schema:

```json
{
  "status": "number (HTTP status code)",
  "message": "string (Error description)",
  "timestamp": "string (ISO timestamp)"
}
```
