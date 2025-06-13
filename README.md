# Tasktracker

A Spring Boot application for tracking and managing tasks and task lists.

## Description

Tasktracker is a robust task management system built with Spring Boot that allows users to create, update, and manage tasks and task lists. The application provides a RESTful API for task management operations.

## Technologies Used

- Java 24
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- Lombok
- Maven

## Features

- Create, read, update, and delete tasks
- Organize tasks into task lists
- Set task priorities (HIGH, MEDIUM, LOW)
- Track task status (OPEN, CLOSED)
- Set deadlines for tasks

## Prerequisites

- Java 24 or higher
- Maven
- Docker and Docker Compose (for containerized deployment)
- PostgreSQL (if running without Docker)

## Setup and Installation

### Using Docker

1. Clone the repository:
   ```
   git clone <repository-url>
   cd Tasktracker
   ```

2. Start the application using Docker Compose:
   ```
   docker-compose up -d
   ```

   This will start both the PostgreSQL database and the application.

### Manual Setup

1. Clone the repository:
   ```
   git clone <repository-url>
   cd Tasktracker
   ```

2. Configure the database:
   - Ensure PostgreSQL is installed and running
   - Create a database named `tasktrackerdb`
   - Create a user `tasktracker` with password `A_Strong_P@ssw0rd_2024!` (or update the credentials in `application.properties`)

3. Build the application:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

   The application will be available at `http://localhost:8082`

## API Endpoints

### Task Lists

- `GET /api/tasklists` - Get all task lists
- `GET /api/tasklists/{id}` - Get a specific task list
- `POST /api/tasklists` - Create a new task list
- `PUT /api/tasklists/{id}` - Update a task list
- `DELETE /api/tasklists/{id}` - Delete a task list

### Tasks

- `GET /api/tasks` - Get all tasks
- `GET /api/tasks/{id}` - Get a specific task
- `POST /api/tasks` - Create a new task
- `PUT /api/tasks/{id}` - Update a task
- `DELETE /api/tasks/{id}` - Delete a task

## Project Structure

```
Tasktracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/virtue/tasktracker/
│   │   │       ├── controller/       # REST controllers
│   │   │       ├── domain/           # Domain models and DTOs
│   │   │       ├── mappers/          # Object mappers
│   │   │       ├── repositories/     # Data repositories
│   │   │       ├── service/          # Business logic
│   │   │       └── TasktrackerApplication.java
│   │   └── resources/
│   │       └── application.properties # Application configuration
│   └── test/                         # Test classes
├── docker-compose.yml                # Docker Compose configuration
├── Dockerfile                        # Docker configuration
├── pom.xml                           # Maven dependencies
└── README.md                         # Project documentation
```

## Testing

Run the tests using Maven:

```
mvn test
```

The test configuration uses an H2 in-memory database to avoid affecting the production database.

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.