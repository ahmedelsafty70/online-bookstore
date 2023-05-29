FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/serviceAccountKey.json src/main/resources/serviceAccountKey.json
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]