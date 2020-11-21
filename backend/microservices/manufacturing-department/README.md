## Setup

### Application Server:
* The compiled Microservice *Manufacturing-Department* already contains the Application Server and can be executed directly inside a Container that has Java version 15 installed. The following Steps are necessary:

```sh
FROM openjdk:15
COPY . /usr/src/manufacturing-department-app
WORKDIR /usr/src/manufacturing-department-app
RUN javac Main.java
CMD ["java", "Main"]
```

```sh
$ docker build -t manufacturing-department-app .
$ docker run -it --rm --name running-manufacturing-department-app manufacturing-department-app
```

* The running Microservices is available at the URL http://localhost:8083.

### Database:

* The Microservice *Manufacturing-Department* needs its own Database. Therefore an Instance of the PostgreSQL Database is set up on a Container. The following steps are necessary:

* The PostgreSQL Version *alpine* is installed inside the Container *postgres-manufacturing-department-container*:

```sh
$ docker run --name postgres-manufacturing-department-container -e POSTGRES_PASSWORD=manufacturing-department -d -p 5435:5432 postgres:alpine
```

* A new Database is created within the Container *postgres-manufacturing-department-container*:

```sh
docker exec -it postgres-manufacturing-department-container /bin/bash
psql -U postgres
CREATE DATABASE manufacturing-department;
```
___

## Einrichtung
### Anwendungsserver:

* Der kompilierte Microservice *Manufacturing-Department* enthält bereist den Anwendungsserver und kann direkt innerhalb eines Containers ausgeführt werden, welcher die Java-Version 15 installiert hat. Dazu sind folgende Schritte notwendig:

```sh
FROM openjdk:15
COPY . /usr/src/manufacturing-department-app
WORKDIR /usr/src/manufacturing-department-app
RUN javac Main.java
CMD ["java", "Main"]
```

```sh
$ docker build -t manufacturing-department-app .
$ docker run -it --rm --name running-manufacturing-department-app manufacturing-department-app
```

* Der laufende Microservices ist erreichbar unter der URL http://localhost:8083.

### Datenbank:

* Der Microservice *Manufacturing-Department* benötigt seine eigene Datenbank. Dafür wird auf einem Container eine Instanz der Datenbank PostgreSQL eingerichtet. Dazu sind folgende Schritte notwendig:

* Die PostgreSQL Version *alpine* wird innerhalb eines Containers *postgres-manufacturing-department-container* installiert:

```sh
$ docker run --name postgres-manufacturing-department-container -e POSTGRES_PASSWORD=manufacturing-department -d -p 5435:5432 postgres:alpine
```

* Es wird eine neue Datenbank innerhalb des Containers *postgres-manufacturing-department-container* angelegt:

```sh
docker exec -it postgres-manufacturing-department-container /bin/bash
psql -U postgres
CREATE DATABASE manufacturing-department;
```
