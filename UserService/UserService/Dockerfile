FROM openjdk:17-jdk-alpine

WORKDIR /userservice

RUN ./gradlew build userservice.jar

COPY ./target/userservice.jar userservice.jar

ENTRYPOINT ["java", "-jar", "/userservice.jar"]
