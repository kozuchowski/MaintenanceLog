FROM maven:3.8-openjdk-18 as builder

WORKDIR /app

COPY pom.xml /app
COPY src /app/src


RUN mvn clean package

FROM openjdk:18-jdk-alpine

COPY --from=builder /app/target/finanseApp_backend-0.0.1-SNAPSHOT.jar /app/maintanancelog-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/maintanancelog-0.0.1-SNAPSHOT.jar"]