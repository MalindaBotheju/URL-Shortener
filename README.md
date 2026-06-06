# 🚀 Dockerized Full-Stack URL Shortener

A production-ready URL Shortener web application built using **Spring Boot**, **Thymeleaf**, and **MySQL**, fully containerized using **Docker** and **Docker Compose**.

## 🛠️ Tech Stack
* **Backend:** Java 17, Spring Boot, Spring Data JPA
* **Frontend:** HTML5, TailwindCSS / Thymeleaf templates
* **Database:** MySQL 8.0
* **Containerization:** Docker, Docker Compose

## 🐳 How to Run via Docker (Quick Start)

You do not need Java or MySQL installed locally to run this application. You only need **Docker Desktop**.

1. Clone this repository:
   ```bash
   git clone https://github.com/MalindaBotheju/URL-Shortener.git
   ```
   ```bash
   cd URL-Shortener
   ```

2. Package the Spring Boot application into a executable .jar file:
   ```bash
   ./mvnw package -DskipTests
   ```

3. Spin up the multi-container environment:
   ```bash
   docker-compose up --build
   ```
   
4. Open your browser and visit: **`http://localhost:8080`**

5. To stop the running containers gracefully, use `Ctrl + C` in the terminal, or run:

   ```bash
   docker-compose down
   ```
