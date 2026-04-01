# Reverse Interview Platform - Backend

Spring Boot REST API for the Reverse Interview Platform.

## Configuration

### Database
- MySQL database named `reverse_platform`
- Default username: `root`
- No password (update in `application.properties`)

### Server
- Runs on port 8080
- CORS enabled for http://localhost:3000

## Running the Application

```bash
mvn clean install
mvn spring-boot:run
```

## API Documentation

### Authentication Endpoints

#### POST /api/login
Login for both candidates and companies.

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "role": "candidate", // or "company"
  "data": {
    // User object based on role
  }
}
```

### Registration Endpoints

#### POST /api/register/candidate
Register a new candidate.

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "skills": "JavaScript, React, Node.js"
}
```

#### POST /api/register/company
Register a new company.

**Request Body:**
```json
{
  "companyName": "Tech Corp",
  "email": "company@techcorp.com",
  "password": "password123",
  "description": "Leading technology company"
}
```

### Data Endpoints

#### GET /api/candidates
Get all registered candidates.

**Response:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "skills": "JavaScript, React, Node.js"
  }
]
```

#### GET /api/companies
Get all registered companies.

**Response:**
```json
[
  {
    "id": 1,
    "companyName": "Tech Corp",
    "email": "company@techcorp.com",
    "description": "Leading technology company"
  }
]
```

## Entity Models

### Candidate
- id: Long (Primary Key)
- name: String
- email: String (Unique)
- password: String
- skills: String

### Company
- id: Long (Primary Key)
- companyName: String
- email: String (Unique)
- password: String
- description: String

### Offer
- id: Long (Primary Key)
- companyId: Long
- candidateId: Long
- message: String

## Security Notes

- Passwords are stored in plain text (use bcrypt in production)
- No JWT implementation (add for production)
- Basic CORS configuration
