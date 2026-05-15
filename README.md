Əlbəttə, layihə sənədini olduğu kimi saxlayaraq (heç bir texniki detalı dəyişmədən) ingilis dilinə tərcümə etdim:Microservices ProjectMicroservice architecture with Spring Cloud Gateway.ArchitectureClient → API Gateway (8080)↓              ↓Profile Service    Feedback Service(8081)              (8082)↓              ↓profile_db      feedback_dbServicesServicePortDescriptionAPI Gateway8080Routing and loggingProfile Service8081User profile CRUDFeedback Service8082Feedback create and listTechnologiesJava 21Spring Boot 4.0.6Spring Cloud Gateway 2025.1.1PostgreSQL 15Docker + Docker ComposeSwagger/OpenAPIRunning the ProjectRequirementsDocker DesktopJava 21Maven 3.9+CommandsBash# 1 — Clone the project
git clone https://github.com/SƏNIN_ADIN/microservices-project.git
cd microservices-project

# 2 — Build the project
mvn clean package -DskipTests

# 3 — Run with Docker
docker-compose up -d --build
API EndpointsProfile ServiceMethodURLDescriptionPOST/api/v1/profilesCreate profileGET/api/v1/profilesGet all profilesGET/api/v1/profiles/{id}Get single profilePUT/api/v1/profiles/{id}Update profileDELETE/api/v1/profiles/{id}Delete profileFeedback ServiceMethodURLDescriptionPOST/api/v1/feedbackCreate feedbackGET/api/v1/feedbackGet all feedbacksSwagger UIhttp://localhost:8081/swagger-ui.html  → Profile Servicehttp://localhost:8082/swagger-ui.html  → Feedback ServiceAPI VersioningUse the Accept header for requests:Accept: application/vnd.profileapp+json;v=1.0Accept: application/vnd.feedbackapp+json;v=1.0LoggingThe Gateway logs every request:→ [requestId] Method: POST | Path: /api/v1/profiles← [requestId] Response Status: 201