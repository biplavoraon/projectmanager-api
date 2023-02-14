
# Build stage
FROM maven:3.8.7-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

# Package stage
FROM eclipse-temurin:17-jdk
COPY --from=build /target/project-manager.jar project-manager.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","project-manager.jar"]

ENV db_password=0
ENV db_url=0
ENV db_username=0
ENV secret_key=0