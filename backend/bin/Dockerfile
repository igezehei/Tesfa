# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the backend JAR file into the container
COPY target/*.jar app.jar

# Expose the port the backend runs on
EXPOSE 8080

# Run the backend application
ENTRYPOINT ["java", "-jar", "app.jar"]