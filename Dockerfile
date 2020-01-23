FROM gradle:5.6.3-jdk12

ADD src src
ADD build.gradle .
ADD settings.gradle .

RUN gradle build

FROM openjdk:12-jdk-alpine

COPY --from=0 /home/gradle/build/libs/apinasa-1.0.jar apinasa-1.0.jar

EXPOSE 8081

ENTRYPOINT exec java $JAVA_OPTS -jar /apinasa-1.0.jar