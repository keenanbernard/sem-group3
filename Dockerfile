FROM openjdk
COPY ./target/sem-group3-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-group3-0.1.0.3-jar-with-dependencies.jar", "db:3306"]