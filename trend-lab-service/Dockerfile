FROM openjdk
WORKDIR usr/lib
EXPOSE 8086
ADD ./target/trend-lab-service-0.0.1-SNAPSHOT.jar /usr/lib/trend-lab-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","trend-lab-service-0.0.1-SNAPSHOT.jar"]