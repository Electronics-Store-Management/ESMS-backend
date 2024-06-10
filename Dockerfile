
# Build stage

FROM maven:3.9.5-amazoncorretto-21 AS build

COPY . .

RUN mvn package -DskipTests spring-boot:repackage


# Package stage

FROM openjdk:21

COPY --from=build /target/classes/production.properties application.properties
COPY --from=build /target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]