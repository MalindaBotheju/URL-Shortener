# Use a lightweight Java 21 image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled jar file from your target folder into the container
COPY target/*.jar app.jar

# Expose port 8080 so we can access the web page
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
