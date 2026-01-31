Mambu Fullstack Skeleton Backend

- This is a skeleton Spring Boot application intended to speed up the challenge solution.
- It has an H2 in-memory database configured by default.
- It has full end-to-end (e2e) configuration so you can run and verify the service as-is.

Quick start
- Requirements: Java 17+
- Run: ./mvnw spring-boot:run
- Base URL: http://localhost:8080

Example API (Tasks)
- GET /api/tasks — list tasks
- GET /api/tasks/{id} — get a task by id
- POST /api/tasks — create { "title": "My task", "completed": false }
- PUT /api/tasks/{id} — update { "title": "New title", "completed": true }
- DELETE /api/tasks/{id} — delete

Notes
- You can freely modify or extend this skeleton for your challenge.