# --- build stage: compile with Maven ---
FROM maven:3.9.6-eclipse-temurin-21 AS build

# set workdir and copy sources
WORKDIR /app
COPY pom.xml ./
COPY src ./src

# build the application (skip tests for speed)
RUN mvn clean package -DskipTests

# --- runtime stage ---
FROM eclipse-temurin:21-jdk
WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# service port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]