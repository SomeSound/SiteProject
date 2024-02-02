FROM ubuntu:latest
ARG JAR_FILE=/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080


#FROM openjdk:21-rc-jdk
#ARG JAR_FILE=/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]
#EXPOSE 8080
