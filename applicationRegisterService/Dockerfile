FROM openjdk
WORKDIR usr/src
EXPOSE 8098
ADD ./target/applicationRegisterService-0.0.1-SNAPSHOT.jar /usr/src/applicationRegisterService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","applicationRegisterService-0.0.1-SNAPSHOT.jar"]