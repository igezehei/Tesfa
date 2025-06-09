# Project Tesfa

## Overview
Project Tesfa is a full-stack application consisting of a Spring Boot backend and a React frontend. It is designed to provide a seamless user experience and robust backend functionality.

## Backend
The backend is built using Spring Boot and connects to a PostgreSQL database. It includes:
- RESTful APIs for data interaction.
- Dockerized setup for easy deployment.
- Database seed scripts for initial data population.

### How to Run the Backend
1. Navigate to the `backend` directory:
   ```bash
   cd backend
   ```
2. Start the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
3. Alternatively, use the `startup.sh` script to start the application and check database connectivity:
   ```bash
   ./startup.sh
   ```

## Frontend
The frontend is built using React and TypeScript. It includes:
- A modular component structure.
- Integration with the backend APIs.

### How to Run the Frontend
1. Navigate to the `frontend` directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```

## Docker Setup
The application is fully dockerized for easy deployment. Use the `docker-compose.yml` file to start both the backend and the PostgreSQL database.

### How to Run with Docker
1. Ensure Docker is installed and running.
2. Start the services:
   ```bash
   docker-compose up
   ```

## Additional Information
- The backend uses a PostgreSQL database. Ensure the database is running and accessible.
- The frontend connects to the backend APIs hosted on `http://localhost:8080`.

## License
This project is licensed under the MIT License.
