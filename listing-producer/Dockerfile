# Build stage
FROM maven:3.8.3-openjdk-17 AS builder
COPY pom.xml /producer/
COPY src /producer/src
RUN mvn -f /producer/pom.xml clean package

# Run stage
FROM openjdk:17-alpine
EXPOSE 8082
COPY --from=builder /producer/target/listings-producer-*.jar producer.jar
ENTRYPOINT ["java","-jar","producer.jar"]