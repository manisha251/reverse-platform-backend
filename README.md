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

### Option 1: Using Maven (Development)
```bash
mvn clean install
mvn spring-boot:run
```

### Option 2: Using Docker (Recommended for Production)

#### Prerequisites
- Docker installed on your system
- Docker Compose (optional, for advanced setup)

#### Quick Start with Docker
```bash
# Build and run with the provided script
./build-and-run.sh  # Linux/Mac
# or
build-and-run.bat   # Windows

# Or manually:
docker build -t reverse-platform .
docker run -p 8080:8080 reverse-platform
```

#### Using Docker Compose (with H2 Console for development)
```bash
# Start the application
docker-compose up

# Start with H2 console (development profile)
docker-compose --profile dev up

# Stop the application
docker-compose down
```

#### Docker Configuration
- **Port**: 8080 (configurable in docker-compose.yml)
- **Health Check**: Automatic health monitoring via Spring Boot Actuator
- **Security**: Runs as non-root user inside container
- **H2 Console**: Available at http://localhost:8181 (when using dev profile)

## API Endpoints

### Authentication
- `POST /api/login` - User login
- `POST /api/register/candidate` - Register new candidate
- `POST /api/register/company` - Register new company

### Candidates
- `GET /api/candidates` - Get all candidates
- `GET /api/candidates/{id}` - Get candidate by ID

### Companies
- `GET /api/companies` - Get all companies
- `GET /api/companies/{id}` - Get company by ID
- `GET /api/dashboard/company/{id}` - Get company dashboard
- `GET /api/verified-companies` - Get verified companies

### Offers
- `POST /api/offers` - Create new offer
- `GET /api/offers` - Get all offers
- `GET /api/offers/candidate/{id}` - Get offers for candidate
- `GET /api/offers/company/{id}` - Get offers from company
- `GET /api/offers/sent/{companyId}` - Get sent offers
- `GET /api/offers/received/{candidateId}` - Get received offers
- `GET /api/offers/stats/company/{id}` - Get offer statistics
- `PUT /api/offers/{id}/status` - Update offer status
- `DELETE /api/offers/{id}` - Delete offer

## Database Console

Access H2 database console at: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:reverse_platform`
- Username: `sa`
- Password: (leave empty)

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
