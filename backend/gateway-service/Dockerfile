FROM adoptopenjdk:15-jre-hotspot

WORKDIR /opt/app

COPY ["./target/gateway-service-0.0.1-SNAPSHOT.jar", "./"]

CMD ["java", "-jar","/opt/app/gateway-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080