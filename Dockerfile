FROM openjdk
COPY ./target/seGroup3.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seGroup3.jar", "db:3306"]