FROM openjdk:17-alpine
MAINTAINER miguel.hurtado
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /tmp
ARG JAR_FILE=build/libs/backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]