# 💼 Job Application Tracker

![Code Coverage](https://codecov.io/gh/jeffgladstone/job-application-tracker/branch/main/graph/badge.svg)

A full-stack web application to track and manage job applications using a modern tech stack.

---

## 🔧 Tech Stack

**Frontend:** React  
**Backend:** Spring Boot (Java 21, Maven)  
**Database:** H2 (local), PostgreSQL (production-ready)  
**API Docs:** Swagger UI (Springdoc OpenAPI 3)

---

## ✨ Features

- Add, edit, and delete job applications
- Track application status: Applied, Interviewing, Offer, Rejected
- Filter applications by status
- RESTful API with Swagger UI docs
- Profile-based environments: `local`, `prod`
- Modern Java 21 setup with Lombok

---

## 🛠 Local Development Setup

### ✅ Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=local
```
- Access H2 Console:
http://localhost:8080/h2-console

- Access Swagger API Docs:
http://localhost:8080/swagger-ui/index.html

### ✅ Frontend (React)

```bash
cd frontend
npm install
npm start
```
Runs on http://localhost:3000 by default

---

## 🔥 Future Features

- User authentication (JWT)
- Interview reminders
- Export to CSV or PDF
- Resume upload & tagging

## 📸 Screenshots
(Adding some UI/API screenshots here  soon!)