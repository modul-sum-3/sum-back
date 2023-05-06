#
# Build stage
#
FROM maven:3.8.7-eclipse-temurin-19 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:19-jdk-slim
COPY --from=build /target/fitnessBack-0.0.1-SNAPSHOT.jar demo.jar
ENV PORT=8080
ENTRYPOINT ["java","-jar","demo.jar"]