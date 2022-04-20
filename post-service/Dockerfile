FROM openjdk
WORKDIR usr/lib
#ENV MONGO_DATABASE=post1
#ENV MONGO_URL=mongodb://localhost:27017/
EXPOSE 9093
ADD ./target/post-service-0.0.1-SNAPSHOT.jar  /usr/lib/post-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","post-service-0.0.1-SNAPSHOT.jar"]