# Library Management System

A RESTful API for managing books, borrowing books, and tracking borrowing history using Spring Boot, Hibernate, and JPA.

## Technologies Used

- Spring Boot 3.5.0
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Spring Validation

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL 12 or higher

### Database Setup

1. Install PostgreSQL if you haven't already
2. Create a new database named 'library'
3. The application is configured to use the following database credentials:
   - URL: `jdbc:postgresql://localhost:5432/library`
   - Username: `postgres`
   - Password: `postgres`

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Maven:
   ```
   ./mvnw spring-boot:run
   ```
4. The application will start on port 8080 by default

## API Endpoints

### Book Management

#### Create a new book
- **URL**: `/api/books`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "title": "Book Title",
    "author": "Author Name",
    "isbn": "1234567890",
    "availabilityStatus": "AVAILABLE"
  }
  ```
- **Response**: The created book with HTTP status 201 (Created)

#### Get book details by ISBN
- **URL**: `/api/books/{isbn}`
- **Method**: `GET`
- **Response**: The book details with HTTP status 200 (OK)

#### Get book availability by ISBN
- **URL**: `/api/books/{isbn}/availability`
- **Method**: `GET`
- **Response**: The availability status ("AVAILABLE" or "BORROWED") with HTTP status 200 (OK)

### Borrowing Transactions

#### Create a new borrowing transaction
- **URL**: `/api/borrowing-transactions`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "isbn": "1234567890",
    "borrowerName": "John Doe",
    "borrowDate": "2023-06-01T10:00:00"
  }
  ```
- **Response**: The created borrowing transaction with HTTP status 201 (Created)

#### Return a borrowed book
- **URL**: `/api/borrowing-transactions/{transactionId}/return`
- **Method**: `PUT`
- **Response**: The updated borrowing transaction with HTTP status 200 (OK)

## Business Rules

- A book can only be borrowed if it is available (status: AVAILABLE)
- When a book is borrowed, its status is updated to BORROWED
- When a book is returned, its status is updated to AVAILABLE and the borrowing transaction status is updated to RETURNED