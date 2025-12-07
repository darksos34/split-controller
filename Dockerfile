# dockerfile
FROM maven:3.9.8-eclipse-temurin-25 AS build

WORKDIR /split-controller

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:25-jre

WORKDIR /split-controller

# set desired runtime port (can be overridden at runtime)
ENV SERVER_PORT=8081

# copy built jar from build stage
COPY --from=build /split-controller/target/*.jar app.jar

EXPOSE 8081

# pass the server.port explicitly so the app binds to the expected container port
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${SERVER_PORT}"]
