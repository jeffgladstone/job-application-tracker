# Use a base JDK image
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy everything from your backend folder
COPY . .

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Run the JAR
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "target/job-application-tracker.jar"]
