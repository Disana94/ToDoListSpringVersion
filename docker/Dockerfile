FROM openjdk:17-ea-3-jdk-slim

ARG APP_JAR=*.jar

COPY ${APP_JAR} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080
