FROM openjdk:latest
COPY ./target/sem-group3-0.1.0.1.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-group3-0.1.0.1.jar"]