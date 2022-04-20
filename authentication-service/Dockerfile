FROM openjdk
WORKDIR usr/lib
EXPOSE 8099
ADD ./target/authentication-service-0.0.1-SNAPSHOT.jar /usr/lib/authentication-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","authentication-service-0.0.1-SNAPSHOT.jar"]

