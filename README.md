# 💼 Job Application Tracker

A full-stack web application to help users track their job applications, built with **React (frontend)** and **Spring Boot (backend)**.

---

## 🚀 Tech Stack

- **Frontend:** React, Axios
- **Backend:** Spring Boot, Maven
- **Database:**
    - Local: H2 (in-memory)
    - Production: PostgreSQL (via Render)
- **Deployment:**
    - Backend: Dockerized on Render
    - Frontend: (Coming soon)

---

## 🛠 Backend Features

| Feature                      | Details |
|-------------------------------|---------|
| Spring Boot REST API          | CRUD endpoints for job applications |
| H2 Database                   | Lightweight DB for local dev |
| PostgreSQL Integration        | For production deployments |
| Liquibase Migrations          | Version-controlled database schema changes |
| Spring Boot Actuator          | App health checks, metrics, and environment info |
| JaCoCo Test Coverage          | Code coverage reports with Codecov badge |
| GitHub Actions CI             | Automated builds + tests on every push |
| Basic Caching (Spring Cache)  | Speeds up repeated `GET` operations |

---

## 🗂 Key Endpoints

| URL Path                      | Description |
|--------------------------------|-------------|
| `/api/applications`           | CRUD operations for job applications |
| `/swagger-ui/index.html`       | OpenAPI/Swagger documentation |
| `/actuator/health`             | Health status (via Actuator) |
| `/actuator/info`               | App metadata |
| `/actuator/metrics`            | JVM metrics, cache metrics, etc. |
| `/actuator/caches`             | Shows active caches |

---

## 🧪 Testing and Code Quality

- **Unit Tests:** JUnit5 + Mockito for service layer
- **Integration Tests:** MockMvc for controller layer
- **Code Coverage:**
    - JaCoCo integrated into Maven
    - Coverage uploaded to Codecov
    - ![Codecov Badge](https://codecov.io/gh/jeffgladstone/job-application-tracker/branch/main/graph/badge.svg)

---

## 📦 Database Management (Liquibase)

- All schema changes are managed via Liquibase YAML changelogs.
- Migration History:
    - `001-initial-schema`: Create `job_application` table
    - `002-add-location-column`: Add `location` field
    - `003-add-salary-column`: Add `salary_expectation` field

---

## ⚡ Setup Instructions (Backend)

1. Clone the repository
2. Go into the backend folder:
    ```bash
    cd backend
    ```
3. Build and run:
    ```bash
    ./mvnw clean package
    java -Dspring.profiles.active=local -jar target/job-application-tracker.jar
    ```

---

## 🎯 Coming Soon

- Frontend React deployment (Netlify/Vercel)
- User authentication (JWT)
- Interview reminders
- Export to CSV or PDF
- Resume upload & tagging
- Admin role + controller

---

# 🚀 Live Demo

| Service       | URL |
|---------------|-----|
| Backend API   | https://job-application-backend.onrender.com |
| Swagger Docs  | https://job-application-backend.onrender.com/swagger-ui/index.html |

---

## 🧠 Author

> Built by Jeff G. — Software Engineer passionate about full-stack development and clean architecture 🚀
