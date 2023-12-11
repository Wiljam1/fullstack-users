FROM maven:3.8.4-openjdk-17-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml install

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/fullstack-users-0.0.1-SNAPSHOT.jar /app/fullstack-users-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/target/fullstack-users.jar"]