FROM amazoncorretto:21-alpine3.19-jdk
WORKDIR /home/Hyper/
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
