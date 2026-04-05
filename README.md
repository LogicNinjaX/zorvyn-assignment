# Finance Data Processing & Access Control Backend

## Overview
This project is a backend system for a **finance dashboard** that manages financial records and provides **role-based access control** along with **summary analytics APIs**.

The system is designed with a focus on **clean architecture, maintainability, and efficient data processing**.

---

## Features

### User & Role Management
- Create and manage users  
- Assign roles: **ADMIN, ANALYST, VIEWER**  
- Manage user status (active/inactive)  
- Role-based access control  

---

### Financial Records Management
- Create, update, delete financial records  
- Fields include:
  - Amount
  - Type (INCOME / EXPENSE)
  - Category
  - Date
  - Description  
- Filter records by:
  - Date range
  - Category
  - Type  

---

### Dashboard APIs
Provides aggregated data for dashboard:

- Total Income  
- Total Expenses  
- Net Balance  
- Category-wise totals  
- Recent transactions  
- Monthly trends  

---

### Access Control
- **Viewer** → Read-only access  
- **Analyst** → Read + analytics  
- **Admin** → Full access  

Implemented using **Spring Security + JWT**

---

### Validation & Error Handling
- Input validation using annotations  
- Global exception handling  
- Meaningful error responses with proper HTTP status codes  

---

## Tech Stack

- Java 21  
- Spring Boot  
- Spring Data JPA (Hibernate)  
- Spring Security (JWT)  
- MySQL
- Maven  

---

## API Endpoints

### Auth
```

POST /api/v1/auth/register
POST /api/v1/auth/login

```

---

### Users (Admin Only)
```

GET /api/v1/users
POST /api/v1/users
PATCH /api/v1/users/{id}
PATCH /api/v1/users/{id}/status
PATCH /api/v1/users/{id}/role
DELETE /api/v1/users/{id}

```

---

### Financial Records
```

POST   /api/v1/transactions
GET    /api/v1/transactions
GET    /api/v1/transactions/{id}
PATCH  /api/v1/transactions/{id}
DELETE /api/v1/transactions/{id}

```

Supports filtering:
```

/transactions?type=INCOME&category=FOOD&startDate=2025-01-01

```

---

### Dashboard
```

GET /dashboard

````

Sample Response:
```json
{
  "totalIncome": 50000,
  "totalExpense": 30000,
  "netBalance": 20000,
  "categorySummary": [...],
  "recentTransactions": [...],
  "monthlyTrends": [...]
}
````

---

## Project Structure

```
controller → service → repository → database
```

* **Controller** → Handles API requests
* **Service** → Business logic
* **Repository** → Database operations
* **DTO** → Data transfer objects
* **Security** → JWT & access control

---

## Assumptions

* Users are authenticated using JWT
* Financial records are linked to users
* Only authorized roles can perform actions
* Dashboard data is calculated from stored records

---

## Improvements (Optional Features)

* Pagination for records
* Search functionality
* Soft delete
* Caching for dashboard
* Unit & integration tests

---

## Key Design Decisions

* Used **single dashboard endpoint** to reduce API calls
* Implemented **DTO projections** for efficient aggregation queries
* Separated **business logic from controllers**
* Used **role-based access control** for security

---

## How to Run

1. Clone the repository
2. Configure database and JWT in `application.yml`
3. Run the application
4. Access api documentation at http://localhost:8080/swagger-ui.html

```
mvn spring-boot:run
```

---

## Conclusion

This project demonstrates backend design principles including:

* Clean architecture
* Data aggregation
* Access control
* API design

The focus is on building a **structured, scalable, and maintainable backend system** rather than unnecessary complexity.

---

## 👨‍💻 Author

**Nitish Sahni**
Backend Developer Intern Candidate
