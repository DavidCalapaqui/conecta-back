FROM maven:3.8.4-openjdk-17 AS builder

COPY pom.xml .
COPY src ./src

# archivo WAR
RUN mvn clean package

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder target/conecta-test-0.0.1-SNAPSHOT.war /app/conecta-test.war
COPY src/main/resources/application.properties /app/application.properties

EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "conecta-test.war"]
