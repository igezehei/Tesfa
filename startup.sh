#!/bin/zsh

# Quick startup script for Tesfa project (backend + frontend)

# Start Docker Compose (if needed)
cd backend
if command -v docker-compose &> /dev/null; then
  echo "Starting Docker Compose (if configured)..."
  docker-compose up -d || echo "Docker Compose not configured or failed. Continuing..."
fi

# Enable Spring Boot Actuator for observability
if ! grep -q 'spring-boot-starter-actuator' ../backend/pom.xml; then
  echo 'Adding Spring Boot Actuator to backend pom.xml...'
  sed -i '' '/<dependencies>/a\
        <dependency>\
            <groupId>org.springframework.boot</groupId>\
            <artifactId>spring-boot-starter-actuator</artifactId>\
        </dependency>' ../backend/pom.xml
fi

# Start backend
if command -v mvn &> /dev/null; then
  echo "Starting Spring Boot backend..."
  mvn spring-boot:run &
  BACKEND_PID=$!
  cd ..
else
  echo "Maven (mvn) not found. Please install Maven."
  exit 1
fi

# Wait a bit for backend to start
sleep 10

# Start frontend
cd frontend
if command -v npm &> /dev/null; then
  echo "Starting React frontend..."
  npm install
  npm start
else
  echo "npm not found. Please install Node.js and npm."
  kill $BACKEND_PID
  exit 1
fi

# When done, kill backend
kill $BACKEND_PID