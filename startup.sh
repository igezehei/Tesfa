#!/bin/bash

# Navigate to the backend directory
cd backend

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
  echo "Docker is not running. Please start Docker and try again."
  exit 1
fi

# Start Docker Compose
if ! docker-compose up -d; then
  echo "Failed to start Docker Compose. Check your configuration and try again."
  exit 1
fi

# Wait for PostgreSQL to be ready
echo "Waiting for PostgreSQL to be ready..."
for i in {1..30}; do
  if docker exec my_database pg_isready -U your_username > /dev/null 2>&1; then
    echo "PostgreSQL is ready."
    break
  fi
  sleep 1
  echo "Retrying PostgreSQL connection... ($i/30)"
  if [ $i -eq 30 ]; then
    echo "PostgreSQL did not become ready in time."
    exit 1
  fi
done

# Check if the database exists
if docker exec my_database psql -U your_username -lqt | cut -d \| -f 1 | grep -qw your_database; then
  echo "Database 'your_database' already exists. Skipping creation."
else
  echo "Database 'your_database' does not exist. Creating database..."
  docker exec my_database psql -U your_username -c "CREATE DATABASE your_database;"
fi

# Start the backend service
if ! mvn spring-boot:run; then
  echo "Failed to start the backend service. Check the logs for details."
  exit 1
fi

# Navigate to the frontend directory
cd ../frontend

# Install dependencies
if ! npm install; then
  echo "Failed to install frontend dependencies. Check the logs for details."
  exit 1
fi

# Start the frontend service
if ! npm start; then
  echo "Failed to start the frontend service. Check the logs for details."
  exit 1
fi

exit 0