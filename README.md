# Microservices Project

Spring Cloud Gateway ilə mikroservis arxitekturası.

## Arxitektura
Client → API Gateway (8080)
↓              ↓
Profile Service    Feedback Service
(8081)              (8082)
↓              ↓
profile_db      feedback_db

## Servislər

| Servis | Port | Təsvir |
|--------|------|--------|
| API Gateway | 8080 | Routing və logging |
| Profile Service | 8081 | İstifadəçi profil CRUD |
| Feedback Service | 8082 | Feedback create və list |

## Texnologiyalar

- Java 21
- Spring Boot 4.0.6
- Spring Cloud Gateway 2025.1.1
- PostgreSQL 15
- Docker + Docker Compose
- Swagger/OpenAPI

## Layihəni işə salmaq

### Tələblər
- Docker Desktop
- Java 21
- Maven 3.9+

### Əmrlər

```bash
# 1 — Layihəni clone et
git clone https://github.com/SƏNIN_ADIN/microservices-project.git
cd microservices-project

# 2 — Build et
mvn clean package -DskipTests

# 3 — Docker ilə işə sal
docker-compose up -d --build
```

## API Endpointləri

### Profile Service
| Method | URL | Təsvir |
|--------|-----|--------|
| POST | /api/v1/profiles | Profil yarat |
| GET | /api/v1/profiles | Hamısını gətir |
| GET | /api/v1/profiles/{id} | Birini gətir |
| PUT | /api/v1/profiles/{id} | Yenilə |
| DELETE | /api/v1/profiles/{id} | Sil |

### Feedback Service
| Method | URL | Təsvir |
|--------|-----|--------|
| POST | /api/v1/feedback | Feedback yarat |
| GET | /api/v1/feedback | Hamısını gətir |

## Swagger UI
http://localhost:8081/swagger-ui.html  → Profile Service
http://localhost:8082/swagger-ui.html  → Feedback Service

## API Versioning

Sorğularda `Accept` header istifadə et:
Accept: application/vnd.profileapp+json;v=1.0
Accept: application/vnd.feedbackapp+json;v=1.0

## Logging

Gateway hər sorğu üçün log yazır:
→ [requestId] Method: POST | Path: /api/v1/profiles
← [requestId] Response Status: 201

