# Stage 1: build the jar using Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Cache deps: copy only pom first for layer caching
COPY pom.xml .
RUN mvn -q -B -ntp dependency:go-offline

# Copy sources and build
COPY src ./src
RUN mvn -q -B -DskipTests package

# Stage 2: runtime image (smaller)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy jar from build stage (adjust path if your JAR name differs)
COPY --from=build /app/target/*.jar app.jar

# Optional: allow callers to pass JVM options
ENV JAVA_OPTS=""

# Do not hardcode port here — we pass the runtime port from the PORT env var at runtime
# Use sh -c to allow environment variable expansion in the ENTRYPOINT
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar --server.port=${PORT} --server.address=0.0.0.0"]
