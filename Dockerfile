FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
ADD target/project-manager.jar project-manager.jar
ENTRYPOINT ["java","-jar","project-manager.jar"]