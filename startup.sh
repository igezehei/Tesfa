#!/bin/zsh

# Quick startup script for Tesfa project (backend + frontend)


echo "Building and starting all services with Docker Compose..."
docker-compose up --build -d

echo "All services are starting in Docker containers."
echo "Backend: http://localhost:8080"
echo "Frontend: http://localhost:3000"