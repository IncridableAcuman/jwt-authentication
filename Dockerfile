FROM gradle:8.8-jdk17 AS build
LABEL authors="Izzatbek"

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .

COPY src ./src

RUN gradle bootJar --no-daemon

FROM openjdk:17-jre-slim AS runtime

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]