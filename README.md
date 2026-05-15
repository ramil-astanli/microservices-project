 
---

# Microservices Project

Microservice architecture with Spring Cloud Gateway.

## Architecture

Client → API Gateway (8080)
↓              ↓
Profile Service    Feedback Service
(8081)              (8082)
↓              ↓
profile_db      feedback_db

## Services

| Service | Port | Description |
| --- | --- | --- |
| API Gateway | 8080 | Routing and logging |
| Profile Service | 8081 | User profile CRUD |
| Feedback Service | 8082 | Feedback create and list |

## Technologies

* Java 21
* Spring Boot 4.0.6
* Spring Cloud Gateway 2025.1.1
* PostgreSQL 15
* Docker + Docker Compose
* Swagger/OpenAPI

## Running the Project

### Requirements

* Docker Desktop
* Java 21
* Maven 3.9+

### Commands

```bash
# 1 — Clone the project
git clone https://github.com/SƏNIN_ADIN/microservices-project.git
cd microservices-project

# 2 — Build the project
mvn clean package -DskipTests

# 3 — Run with Docker
docker-compose up -d --build

```

## API Endpoints

### Profile Service

| Method | URL | Description |
| --- | --- | --- |
| POST | /api/v1/profiles | Create profile |
| GET | /api/v1/profiles | Get all profiles |
| GET | /api/v1/profiles/{id} | Get single profile |
| PUT | /api/v1/profiles/{id} | Update profile |
| DELETE | /api/v1/profiles/{id} | Delete profile |

### Feedback Service

| Method | URL | Description |
| --- | --- | --- |
| POST | /api/v1/feedback | Create feedback |
| GET | /api/v1/feedback | Get all feedbacks |

## Swagger UI

http://localhost:8081/swagger-ui/index.html  → Profile Service
http://localhost:8082/swagger-ui/index.html  → Feedback Service

## API Versioning

Use the `Accept` header for requests:
Accept: application/vnd.profileapp+json;v=1.0
Accept: application/vnd.feedbackapp+json;v=1.0

## Logging

The Gateway logs every request:
→ [requestId] Method: POST | Path: /api/v1/profiles
← [requestId] Response Status: 201