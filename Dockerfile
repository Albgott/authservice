FROM openjdk:17-jdk-slim
COPY target/authservice.jar authservice.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","authservice.jar"]