FROM openjdk:21-ea-17-slim-buster
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT java -jar /app.jar --CONFIG_SERVER=$CONFIG_SERVER --APP_PROFILE=$APP_PROFILE