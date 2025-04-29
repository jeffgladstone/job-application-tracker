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
    - Frontend: Static site on Render

---

## 🛠 Backend Features

| Feature                      | Details                                          |
|------------------------------|--------------------------------------------------|
| Spring Boot REST API         | CRUD endpoints for job applications              |
| H2 Database                  | Lightweight DB for local dev                     |
| PostgreSQL Integration       | For production deployments                       |
| Liquibase Migrations         | Version-controlled database schema changes       |
| Spring Boot Actuator         | App health checks, metrics, and environment info |
| JaCoCo Test Coverage         | Code coverage reports with Codecov badge         |
| GitHub Actions CI            | Automated builds + tests on every push           |
| Basic Caching (Spring Cache) | Speeds up repeated `GET` operations              |
| Mapstruct Mappings           | For type-safe mapping between entities and DTOs  |
| @ControllerAdvice            | Globally catch exceptions across controllers     |
| JWT Authentication           | Stateless, token-based security                  |

---

## 🗂 Key Endpoints

| URL Path                        | Description                          |
|---------------------------------|--------------------------------------|
| `/api/applications`             | CRUD operations for job applications |
| `/swagger-ui/index.html`        | OpenAPI/Swagger documentation        |
| `/actuator/health`              | Health status (via Actuator)         |
| `/actuator/info`                | App metadata                         |
| `/actuator/metrics`             | JVM metrics, cache metrics, etc.     |
| `/actuator/caches`              | Shows active caches                  |

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
    - `001-initial-schema`: Create `job_applications` table
    - `002-add-location-column`: Add `location` field
    - `003-add-salary-column`: Add `salary_expectation` field
    - `004-create-user-schema`: Create `users` table

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

## ⚡ Setup Instructions (Frontend)

1. Clone the repository
2. Go into the frontend folder:
    ```bash
    cd frontend
    ```
3. Install Node.js dependencies:
    ```bash
    npm install
    ```
4. Set environment variables
- Create a .env file or a .env.development file in the project root.
- Example:
    ```bash
    REACT_APP_API_URL=http://localhost:8080/api/applications
    ```

- For development, point to your locally running backend.
- In production, set REACT_APP_API_URL to your deployed backend URL (e.g., on Render).

5. Start the development server
    ```bash
    npm start
    ```
   This will start the frontend at:
    ```bash
    http://localhost:3000
    ```
    It will automatically proxy requests to your backend API.


6. Build for Production

- To create a production build:
   ```bash
   npm run build
   ```

   This generates static files in the /build folder, ready for hosting (e.g., Render static site hosting).
---

## 🎯 Coming Soon

- Frontend React deployment (Netlify/Vercel)
- User authentication (JWT)
- Interview reminders
- Export to CSV or PDF
- Resume upload & tagging
- Admin role + controller
- Backend validation (@Valid annotations)

---

# 🚀 Live Demo

| Service       | URL |
|---------------|-----|
| Backend API   | https://job-application-tracker-9xyh.onrender.com/api/applications |
| Swagger Docs  | https://job-application-backend.onrender.com/swagger-ui/index.html |
| Frontend Site | https://job-application-tracker-1-15jv.onrender.com/ |

---

## 🧠 Author

> Built by Jeff Gladstone — Software Engineer passionate about full-stack development and clean architecture 🚀
