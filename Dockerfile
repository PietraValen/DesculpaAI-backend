# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests=true -B

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-noble

WORKDIR /app

# Create non-root user for security
RUN useradd -m -u 1000 appuser

# Copy the built JAR from builder
COPY --from=builder /app/target/*.jar app.jar

# Change ownership to appuser
RUN chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:${PORT:-8080}/actuator/health || exit 1

# Expose port (Render uses PORT env variable)
EXPOSE ${PORT:-8080}

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
