FROM maven:3.9.2-eclipse-temurin-17-alpine as build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install -DskipTests

FROM amazoncorretto:17.0.8-alpine3.18

COPY --from=build /app/target/products-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
