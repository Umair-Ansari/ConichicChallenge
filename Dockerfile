FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/conichi-challenge-application-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-XX:+UseSerialGC -Xss1024k -XX:MaxRAM=512m"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar