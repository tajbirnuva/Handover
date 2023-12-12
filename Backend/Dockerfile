#FROM java:8
#FROM adoptopenjdk/openjdk17:alpine-jre
FROM openjdk:17-jdk-slim
LABEL maintainer="info@javabydeveloper.com"
WORKDIR /myapp
COPY target/backend-0.0.1-SNAPSHOT.jar  /myapp/backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
EXPOSE 8585