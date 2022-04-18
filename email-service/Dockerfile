FROM openjdk
WORKDIR /usr/src
ADD ./target/email-service-0.0.1-SNAPSHOT.jar /usr/src/email-service-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","email-service-0.0.1-SNAPSHOT.jar "]