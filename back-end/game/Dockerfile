# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/*.jar app.jar

# Start the app
CMD ["java", "-jar", "app.jar"]
