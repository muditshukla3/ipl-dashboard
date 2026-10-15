FROM eclipse-temurin:21-jre-alpine AS transformer

WORKDIR /application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

# Second stage
FROM eclipse-temurin:21-jre-alpine

ARG USER_ID=5050
ARG GROUP_ID=5050

#RUN addgroup -S spring && adduser -S spring -G spring
RUN apk update && apk upgrade && apk add --no-cache jq tzdata &&\
    addgroup -g ${GROUP_ID} spring &&\
    adduser -S -u ${USER_ID} -g spring spring &&\
    install -d -m 0755 -o spring -g spring /home/spring

USER spring:spring

WORKDIR /app

#Using multi stage layered builds
COPY --chown=5050:5050 --from=transformer /application/extracted/dependencies/ ./
COPY --chown=5050:5050 --from=transformer /application/extracted/snapshot-dependencies/ ./
COPY --chown=5050:5050 --from=transformer /application/extracted/application/ ./

# Entrypoint to app 
ENTRYPOINT ["java", "-jar", "application.jar"]