FROM adoptopenjdk:15-jre-hotspot

WORKDIR /opt/app

COPY ["./target/config-server-0.0.1-SNAPSHOT.jar", "./"]

RUN mkdir /opt/app/config-repo

COPY ["../config-repository", "/opt/app/config-repo"]

CMD ["java", "-jar","/opt/app/config-server-0.0.1-SNAPSHOT.jar"]

VOLUME /opt/app/config-repo

EXPOSE 8888