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
   cd URL-Shortener
   ```

2. Package the Spring Boot application:
   ```bash
   ./mvnw package -DskipTests
   ```

3. Spin up the multi-container environment:
   ```bash
   docker-compose up --build
   ```
   
4. Open your browser and visit: **`http://localhost:8080`**

## 🛑 Shutting Down

To stop the running containers gracefully, use `Ctrl + C` in the terminal, or run:

```bash
docker-compose down

```

```

---

### 🚀 Step 2: Push the Final Polish to GitHub

Once you've saved that file, open your terminal and push this final update using your new authentication token:

```bash
git add README.md
git commit -m "Docs: Add professional README with Docker instructions"
git push origin main

```

When you refresh your GitHub page, your code will be beautifully presented with a clear instructional guide beneath it.

Where would you like to take this engine next? We can look at adding **Custom Short Links** (letting users pick their own short alias), or dive into adding a **Link Expiration/TTL** feature so short codes automatically delete themselves after 24 hours!
