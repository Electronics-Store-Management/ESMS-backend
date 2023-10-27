
# Build stage

FROM maven:3.9.5-amazoncorretto-21 AS build

COPY . .

RUN #mvn clean package -Pprod -DskipTests
RUN mvn clean install


# Package stage

FROM openjdk:21

COPY --from=build /target/classes .
COPY --from=build /target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]