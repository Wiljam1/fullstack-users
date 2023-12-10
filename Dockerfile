FROM maven:3.8.4-openjdk-21-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml install

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/PatientApi-1.0.jar /app/PatientApi-1.0.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/PatientApi-1.0.jar"]