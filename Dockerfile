# 1) Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn -B -q dependency:go-offline/src
RUN mvn -B -DskipTests clean package

# 2)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
CMD ["sh","-c","java -jar app.jar --server.port=${PORT:-8080}"]
