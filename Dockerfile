FROM amazoncorretto:21-alpine-jdk

WORKDIR /home/Hyper/

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
