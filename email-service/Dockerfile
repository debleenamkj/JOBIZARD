FROM openjdk:11
ADD ./target/email-service-0.0.1-SNAPSHOT.jar /usr/src/email-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
EXPOSE 8085
ENTRYPOINT ["java","-jar", "email-service-0.0.1-SNAPSHOT.jar"]
