FROM openjdk
COPY ./target/sem-group3.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-group3.jar", "db:3306", "30000"]