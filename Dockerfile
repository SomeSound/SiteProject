FROM ubuntu:22.10
WORKDIR /app
COPY /*.jar app
ENTRYPOINT ["./app"]
EXPOSE 15671

#FROM openjdk:21-rc-jdk
#ARG JAR_FILE=/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]
#EXPOSE 8080
