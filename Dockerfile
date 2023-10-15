# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Set the ARG for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file to the container
COPY ${JAR_FILE} app.jar

# Specify the command to run your application
CMD ["java", "-jar", "/app.jar"]