FROM openjdk
WORKDIR usr/src
EXPOSE 8099
ADD ./target/authenticationService-0.0.1-SNAPSHOT.jar /usr/src/authenticationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","authenticationService-0.0.1-SNAPSHOT.jar"]

