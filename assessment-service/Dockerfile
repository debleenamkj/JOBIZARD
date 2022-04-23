FROM openjdk
WORKDIR usr/lib
#ENV MONGO_DATABASE=demo1
#ENV MONGO_URL=mongodb://localhost:27017/demo1
EXPOSE 8081
ADD ./target/assessment-service-0.0.1-SNAPSHOT.jar /usr/lib/assessment-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","assessment-service-0.0.1-SNAPSHOT.jar"]