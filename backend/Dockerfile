FROM maven:latest
RUN mvn -B -DskipTests clean package

FROM openjdk:17-jdk-slim


WORKDIR /app

COPY target/*.jar rezervacny_system.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "rezervacny_system.jar"]
