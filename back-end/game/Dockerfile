# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim As build

# Set the working directory to /app
WORKDIR /app

# Copy pom.xml and download dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY ./src ./src
RUN ./mvnw clean install
# Build the application
RUN ./mvnw -B package

# Set base image for runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar file into the container at /app
COPY --from=build app/target/*.jar app/*.jar

# Start the app
CMD ["java", "-jar", "app/*.jar"]
