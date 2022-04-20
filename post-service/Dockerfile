FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=post1
ENV MONGO_URL=mongodb://localhost:27017/
ADD ./target/postImage-service-0.0.1-SNAPSHOT.jar  /usr/lib/postImage-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","postImage-service-0.0.1-SNAPSHOT.jar"]