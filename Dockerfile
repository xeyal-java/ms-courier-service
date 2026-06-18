FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY build/libs/ms-courier-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.datasource.url=jdbc:postgresql://postgres-db:5432/courier_db", "--spring.rabbitmq.host=rabbitmq-server"]