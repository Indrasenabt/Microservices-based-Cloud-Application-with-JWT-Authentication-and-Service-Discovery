# Microservices-based-Cloud-Application-with-JWT-Authentication-and-Service-Discovery
I developed a microservices architecture that consists of multiple independent services — auth-service, accounts-service, and file-service — all orchestrated using Eureka Server for service discovery and a Spring Cloud Gateway for API routing and security enforcement.


TECHNOLOGIES USED:
Spring Boot, Spring Security, Spring Data JPA

JWT for authentication

Eureka Server (Netflix OSS) for service discovery

Spring Cloud Gateway for API routing and filtering

PostgreSQL for persistence

Maven for build automation

RESTful API design principles

LEARNINGS:
Designed stateless authentication using JWT, enabling secure and scalable token validation without session storage.

Implemented role-based access control by embedding roles in JWT claims and verifying them at service endpoints.

Set up a service discovery mechanism to decouple service locations from clients, enabling scalability and resilience.

Configured a cloud gateway to centralize request routing and security, reducing complexity for service consumers.

Managed multi-service configuration including database connections, security, and routing in distributed environment.

Learned troubleshooting real-world issues like port conflicts, service registration case sensitivity, and inter-service communication via gateway.

TESTING AND VALIDATION:
Verified service registration on Eureka Dashboard for all microservices.

Tested user registration and login flows through the cloud gateway successfully without tokens.

Tested secured endpoints requiring JWT tokens, confirming unauthorized requests get 401 errors, and authorized requests succeed.

Validated role-based authorization by accessing user-specific and admin-specific endpoints.

Performed API tests using curl and Postman through the gateway to simulate client requests.

