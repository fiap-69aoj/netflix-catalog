FROM openjdk:8-jdk-alpine

LABEL source="https://github.com/fiap-69aoj/netflix-catalog" \
      maintainer="ewertondsdias@gmail.com"

ADD ./target/catalog-0.0.1-SNAPSHOT.jar catalog.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/catalog.jar"]