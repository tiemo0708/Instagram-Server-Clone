# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Add Maintainer Info
LABEL maintainer="your-email@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Set the working directory
WORKDIR /app

# Copy only the files necessary for the build first
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy the source code
COPY src ./src

# Run the gradle build to create the executable JAR
RUN ./gradlew build --no-daemon

# Copy the generated JAR file to the root of the container
COPY build/libs/Instagram-Server-Clone-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the startup command to run your JAR
CMD ["java", "-jar", "/app/app.jar"]
