FROM openjdk
WORKDIR user/lib
EXPOSE 8083
ADD ./target/recommendation-service-0.0.1-SNAPSHOT.jar /user/lib/recommendation-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","recommendation-service-0.0.1-SNAPSHOT.jar"]