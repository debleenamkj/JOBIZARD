FROM openjdk
WORKDIR usr/lib
EXPOSE 8087
ADD ./target/resources-service-0.0.1-SNAPSHOT.jar /usr/lib/resources-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","resources-service-0.0.1-SNAPSHOT.jar"]