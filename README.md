# Dogs API

A RESTful API built using **Spring Boot** for managing police registered dogs. The application provides CRUD operations for dogs, lookup APIs for reference data, pagination, filtering, validation, soft deletion, and OpenAPI documentation.

---

# Technology Stack

| Technology        | Version  |
| ----------------- | -------- |
| Java              | 17       |
| Spring Boot       | 4.0.7    |
| Spring Web        | Included |
| Spring Data JPA   | Included |
| MySQL             | 8.0      |
| Maven             | Latest   |
| Lombok            | 1.18.38  |
| MapStruct         | 1.6.3    |
| Springdoc OpenAPI | 3.0.2    |

---

# Features

## Dogs

* Create a dog
* Retrieve all dogs
* Retrieve a dog by ID
* Update a dog
* Soft delete a dog

## Additional Features

* RESTful API design
* JSON request/response
* Pagination
* Combined filtering
* DTO-based architecture
* MapStruct entity/DTO mapping
* Global exception handling
* Bean Validation
* Swagger/OpenAPI documentation
* Lookup APIs
* Soft delete implementation

---

# Project Structure

```
dogs
│
├── pom.xml
├── README.md
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.assessment.dogs
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── exception
│   │   │       ├── mapper
│   │   │       ├── repository
│   │   │       └── service
│   │   │
│   │   └── resources
│   │       ├── application.properties
│   │       └── sql
│   │           ├── create_database.sql
│   │           └── insert_sample_data.sql
│   │
│   └── test
│
└── target
```

---

# Database Setup

The application uses **MySQL**.

## Step 1 - Create the Database

Execute the following SQL script:

```
src/main/resources/sql/create_database.sql
```

This script creates:

* Database `dogsdb`
* Database user
* Required privileges

---

## Step 2 - Configure Database

Update:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dogsdb
spring.datasource.username=dogs_user
spring.datasource.password=dogs_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Step 3 - Start the Application

Run the application.

Hibernate automatically creates all database tables from the JPA entity classes:

* Dog
* Breed
* Supplier
* Status
* LeavingReason

No table creation SQL scripts are required.

---

## Step 4 - Seed Lookup Data

On application startup, the `DataInitializer` automatically inserts lookup data into:

* Breeds
* Suppliers
* Statuses
* Leaving Reasons

The initializer only inserts missing records, making it safe to run multiple times.

---

## Step 5 - Insert Sample Dogs

After the tables have been created, execute:

```
src/main/resources/sql/insert_sample_data.sql
```

This inserts sample dog records for testing.

---

# Running the Application

Clone the repository:

```bash
git clone https://github.com/<your-username>/dogs.git
```

Navigate to the project:

```bash
cd dogs
```

Build:

```bash
mvn clean install
```

Run:

```bash
mvn spring-boot:run
```

Application URL:

```
http://localhost:8080
```

---

# API Documentation

Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

---

# REST Endpoints

Base URL

```
/api/dogs
```

---

## Dogs

| Method | Endpoint              | Description        |
| ------ | --------------------- | ------------------ |
| POST   | `/api/dogs/dogs`      | Create a dog       |
| GET    | `/api/dogs/dogs`      | Retrieve all dogs  |
| GET    | `/api/dogs/dogs/{id}` | Retrieve dog by ID |
| PUT    | `/api/dogs/dogs/{id}` | Update dog         |
| DELETE | `/api/dogs/dogs/{id}` | Soft delete dog    |

---

## Lookup APIs

### Breeds

| Method | Endpoint                |
| ------ | ----------------------- |
| GET    | `/api/dogs/breeds`      |
| GET    | `/api/dogs/breeds/{id}` |

### Suppliers

| Method | Endpoint                   |
| ------ | -------------------------- |
| GET    | `/api/dogs/suppliers`      |
| GET    | `/api/dogs/suppliers/{id}` |

### Statuses

| Method | Endpoint                  |
| ------ | ------------------------- |
| GET    | `/api/dogs/statuses`      |
| GET    | `/api/dogs/statuses/{id}` |

### Leaving Reasons

| Method | Endpoint                         |
| ------ | -------------------------------- |
| GET    | `/api/dogs/leaving-reasons`      |
| GET    | `/api/dogs/leaving-reasons/{id}` |

---

# Pagination

The dogs endpoint supports Spring Data pagination.

Example:

```
GET /api/dogs/dogs?page=0&size=10
```

---

# Filtering

The dogs endpoint supports combined filtering using the `filter` query parameter.

Supported fields:

* name
* breed
* supplier

Example:

```
GET /api/dogs/dogs?filter={"name":"Rex"}
```

Example:

```
GET /api/dogs/dogs?filter={"breed":"German Shepherd"}
```

Example:

```
GET /api/dogs/dogs?filter={"supplier":"National Police Kennels"}
```

Combined filter:

```
GET /api/dogs/dogs?filter={"name":"Rex","breed":"German Shepherd","supplier":"National Police Kennels"}
```

---

# Example Create Dog Request

```
POST /api/dogs/dogs
```

```json
{
  "name": "Thor",
  "breedId": 2,
  "supplierId": 3,
  "badgeId": "DOG011",
  "gender": "Male",
  "birthDate": "2022-01-10",
  "dateAcquired": "2023-03-01",
  "statusId": 2,
  "leavingReasonId": null,
  "kennelCharacteristic": "Highly obedient and energetic."
}
```

---

# Soft Delete

Deleting a dog does **not** remove it from the database.

Instead:

```
deleted = true
```

is set.

Soft deleted dogs:

* are excluded from list endpoints
* cannot be retrieved by ID
* cannot be updated

This preserves audit history.

---

# Validation

The application validates incoming requests using Jakarta Bean Validation.

Examples include:

* Required fields
* Valid lookup IDs
* Valid request payloads

Invalid requests return:

```
400 Bad Request
```

with a descriptive error message.

---

# Error Handling

Global exception handling is implemented using `@RestControllerAdvice`.

Example response:

```json
{
  "timestamp": "2026-07-17T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Dog not found with id: 100",
  "path": "/api/dogs/dogs/100"
}
```

Handled exceptions include:

* Resource not found
* Bad request
* IllegalArgumentException

---

# SQL Scripts

The project includes the following SQL scripts:

| Script                                          | Purpose                                                     |
| ----------------------------------------------- | ----------------------------------------------------------- |
| `src/main/resources/sql/create_database.sql`    | Creates the `dogsdb` database, user, and grants privileges. |
| `src/main/resources/sql/insert_sample_data.sql` | Inserts sample dog records into the `dogs` table.           |

Lookup data is automatically inserted by the `DataInitializer` on application startup.

---

# Testing

The API can be tested using:

* Swagger UI


Suggested testing flow:

1. Run `create_database.sql`.
2. Configure `application.properties`.
3. Start the application.
4. Allow JPA to create the tables.
5. Execute `insert_sample_data.sql`.
6. Open Swagger UI 
7. Test CRUD operations, filtering, pagination, lookup APIs, validation, and soft delete.

---

# Notes

* No authentication is required.
* All endpoints consume and produce `application/json`.
* Soft delete is implemented for audit purposes.
* Lookup data is managed separately from dog records.
* Entity-to-DTO mapping is implemented using MapStruct.

---

# Author

Developed as part of the **Dogs API Technical Assessment**.
