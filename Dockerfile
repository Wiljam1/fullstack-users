FROM openjdk:21

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw # clean up the file in mvnw.
RUN ./mvnw dependency:resolve
COPY src ./src

CMD ["./mvnw", "spring-boot:run"]