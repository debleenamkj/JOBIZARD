FROM openjdk
WORKDIR usr/lib
EXPOSE 8089
ADD ./target/chatroom-service-0.0.1-SNAPSHOT.jar /usr/lib/chatroom-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","chatroom-service-0.0.1-SNAPSHOT.jar"]