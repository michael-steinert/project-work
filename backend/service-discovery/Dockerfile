FROM adoptopenjdk:15-jre-hotspot

WORKDIR /opt/app

COPY ["./target/service-discovery-0.0.1-SNAPSHOT.jar", "./"]

CMD ["java", "-jar","/opt/app/service-discovery-0.0.1-SNAPSHOT.jar"]

EXPOSE 8090