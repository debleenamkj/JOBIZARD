FROM openjdk
WORKDIR usr/src
EXPOSE 8098
ADD ./target/application-register-service-0.0.1-SNAPSHOT.jar /usr/src/application-register-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","application-register-service-0.0.1-SNAPSHOT.jar"]