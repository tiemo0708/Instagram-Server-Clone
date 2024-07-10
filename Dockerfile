# Use a base image with Java and Gradle
FROM gradle:7.3.3-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and the build.gradle file
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle

# Download dependencies
RUN ./gradlew build --no-daemon || return 0

# Copy the source code and build the application
COPY src ./src
RUN ./gradlew build --no-daemon

# Use a base image with only Java to run the application
FROM openjdk:17-jdk-slim

# Install netcat (nc)
RUN apt-get update && apt-get install -y netcat

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Run the application
ENTRYPOINT ["sh", "-c", "while ! nc -z db 3306; do sleep 1; done; java -jar /app/app.jar"]
