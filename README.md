# BookFair Project

BookFair is a full-stack, microservices-based system for managing book fairs, stalls, and reservations. The repo includes a React + Vite frontend and a Spring Boot backend with service discovery and an API gateway.

## Repository layout
- `frontend-bookfair/`: React + TypeScript (Vite) UI.
- `backend/`: Spring Boot microservices (Eureka, Gateway, User, Stall, Notification).

## Services and ports (local defaults)
- `eureka-server` (service discovery): `http://localhost:8761`
- `gateway` (API gateway): `http://localhost:8087`
- `user-service` (auth/users): `http://localhost:8083`
- `stall-service` (book fairs, halls, stalls, reservations): `http://localhost:8081`
- `notification-service` (email + QR): `http://localhost:8084`

## Prerequisites
- Java 21 (all backend services use it)
- Node.js + npm (frontend)
- PostgreSQL (local databases)
- RabbitMQ (local message broker)

## Backend setup
1) Start RabbitMQ:
```bash
cd backend/user-service
docker compose up -d
```
2) Create local PostgreSQL databases:
- `bf2-vendor`
- `bf2-stall`
- `notificationdb`

3) Set required environment variables (or load from `.env.example` files):
- `SECURITY_JWT_SECRET`
- `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`
- `MAIL_HOST`, `MAIL_USER`, `MAIL_PASS` (notification service)
- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` (if non-default)

Note: `backend/notification-service/src/main/resources/application.properties` includes sample mail credentials; override them with environment variables for local or production use.

4) Start services (PowerShell example):
```bash
cd backend/eureka-server
.\mvnw.cmd spring-boot:run

cd ../user-service
.\mvnw.cmd spring-boot:run

cd ../stall-service
.\mvnw.cmd spring-boot:run

cd ../notification-service
.\mvnw.cmd spring-boot:run

cd ../gateway
.\mvnw.cmd spring-boot:run
```

## Frontend setup
```bash
cd frontend-bookfair
npm install
npm run dev
```

If you want the frontend to point to the gateway, set `VITE_API_BASE_URL` (see `frontend-bookfair/env`).

## API routing (via gateway)
The gateway routes common paths to services:
- `/api/email/**` and `/api/qr/**` -> `notification-service`
- `/api/bookfairs/**`, `/api/stall-allocations/**`, `/api/stalls/**`, `/api/stall-reservation/**`, `/api/halls/**`, `/api/hall-stalls/**` -> `stall-service`
- `/auth/**`, `/api/users/**`, `/api/stall-users/**`, `/api/me/**` -> `user-service`

## Swagger/OpenAPI
Each service exposes Swagger UI at:
- `http://localhost:<service-port>/swagger-ui.html`
