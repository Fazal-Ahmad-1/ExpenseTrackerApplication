# 🚀 Expense Tracker Backend — Spring Boot

A production-ready **Spring Boot backend** for a personal Expense Tracker application.
It provides clean REST APIs for user authentication, expense management (CRUD), and monthly analytics.
Built for real-world use with proper database integration and deployment support.

---

## ✨ Features

### 🔐 User Management

* Create new user accounts
* Secure login using BCrypt password encoding
* Delete user accounts

### 💵 Expense Management (CRUD)

* Add new expense entries
* View all entries for a specific user
* Update existing expenses
* Delete expenses
* Auto-stores date & payment mode

### 📊 Monthly Analytics

Generates accurate month-wise reports using `YearMonth`:

* Total spent
* Average daily spent
* Highest single expense

### 🗄 Database Integration

* Spring Data JPA + Hibernate
* MySQL / PostgreSQL support
* Automatic schema creation (`ddl-auto=update`)

### 🌐 Ready for Deployment

* Configurable environment variables
* Works seamlessly with **Render** (backend) & **Vercel** (frontend)
* CORS enabled

---

## 🛠️ Tech Stack

| Layer        | Technology                  |
| ------------ | --------------------------- |
| Backend      | Spring Boot (Java)          |
| Security     | BCryptPasswordEncoder       |
| Database     | MySQL / PostgreSQL          |
| ORM          | Spring Data JPA / Hibernate |
| Build Tool   | Maven                       |
| Architecture | REST APIs                   |

---

## 📁 Project Structure

```
src/main/java/com.FazalProject.ExpenseTracker
│
├── Controller
│   ├── UserController
│   └── EntryController
│
├── Entity
│   ├── User
│   ├── Entry
│   ├── LoginDTO
│   ├── UserStats
│   └── PaymentMode
│
├── Repository
│   ├── UserRepository
│   └── EntryRepository
│
└── Service
    ├── UserService
    └── EntryService
```

---

## 🔗 API Endpoints

### 👤 User APIs

| Method | Endpoint                                | Description            |
| ------ | --------------------------------------- | ---------------------- |
| POST   | `/user/create`                          | Register new user      |
| POST   | `/user/login`                           | User login             |
| DELETE | `/user/delete/{username}`               | Delete user            |
| GET    | `/user/{username}/entries`              | Fetch user's entries   |
| GET    | `/user/{username}/{month}/{year}/stats` | Monthly spending stats |

### 💰 Expense APIs

| Method | Endpoint                   | Description       |
| ------ | -------------------------- | ----------------- |
| POST   | `/entry/{username}/create` | Add expense entry |
| PUT    | `/entry/{eid}/update`      | Update entry      |
| DELETE | `/entry/{eid}/delete`      | Remove entry      |

---

## ⚙️ Environment Variables (Production)

Configure these when deploying to Render:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://HOST:PORT/DBNAME
SPRING_DATASOURCE_USERNAME=YOUR_DB_USERNAME
SPRING_DATASOURCE_PASSWORD=YOUR_DB_PASSWORD
PORT=8080
```

---

## ▶️ Run Locally

### 1. Clone repo

```bash
git clone https://github.com/YOUR_USERNAME/expense-tracker-backend.git
cd expense-tracker-backend
```

### 2. Set up MySQL (or PostgreSQL)

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

### 3. Start the app

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

## 🌍 Deployment Guide (Free)

### Backend → Render

* Build Command:

  ```
  ./mvnw clean package -DskipTests
  ```
* Start Command:

  ```
  java -jar target/*.jar
  ```
* Add environment variables
* Connect to a free PostgreSQL instance

### Frontend → Vercel

* Deployed React UI
* Replace API base URL with backend’s Render link

---

## ⭐ Acknowledgements

This backend powers the **Expense Tracker Full-Stack App** built with **React + Spring Boot** and deployed using **Vercel + Render**.

---


