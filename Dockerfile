# ---------- Stage 1: Build ----------
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build

COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .
COPY .env .
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src/ src/

RUN ./mvnw clean package -DskipTests


# ---------- Stage 2: Run ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy built WAR/JAR
COPY --from=builder /build/target/*.war app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]