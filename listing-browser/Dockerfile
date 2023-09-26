# Build stage
FROM maven:3.8.3-openjdk-17 AS builder
COPY pom.xml /browser/
COPY src /browser/src
RUN mvn -f /browser/pom.xml clean package

# Run stage
FROM openjdk:17-alpine
EXPOSE 8081
COPY --from=builder /browser/target/listing-browser-*.jar browser.jar
ENTRYPOINT ["java","-jar","browser.jar"]