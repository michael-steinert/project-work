## Setup

### Application-Server:
* The compiled microservices already contain their application servers and can be executed directly inside a container that has Java version 15 installed. The following steps are necessary:

```sh
FROM openjdk:15
COPY . /usr/src/customer-management-app
WORKDIR /usr/src/customer-management-app
RUN javac Main.java
CMD ["java", "Main"]
```

```sh
$ docker build -t customer-management-app .
$ docker run -it --rm --name running-customer-management-app customer-management-app
```

### Database:

* The Microservice Customer-Management needs its own database. For this Purpose, an instance of the PostgreSQL Database must be set up on a container. The following Steps are necessary::

* The PostgreSQL Version *alpine* is installed inside a Container:

```sh
$ docker run --name postgres-customer-management-container -e POSTGRES_PASSWORD=customer-management -d -p 5433:5432 postgres:alpine
```

* A new Database must be created within the Container:

```sh
docker exec -it postgres-customer-management-container /bin/bash
psql -U postgres
CREATE DATABASE customer_management;
```
___

## Einrichtung
### Anwendungsserver:

* Die kompilierten Microservices enthalten bereist deren Anwendungsserver und können direkt innerhalb eines Containers ausgeführt werden, welcher die Java-Version 15 installiert hat. Das sind folgende Schritte notwendig:

```sh
FROM openjdk:15
COPY . /usr/src/customer-management-app
WORKDIR /usr/src/customer-management-app
RUN javac Main.java
CMD ["java", "Main"]
```

```sh
$ docker build -t customer-management-app .
$ docker run -it --rm --name running-customer-management-app customer-management-app
```

### Datenbank:

* Der Microservice Customer-Management benötigt seine eigene Datenbank. Dafür muss auf einem Container eine Instanz der Datenbank PostgreSQL eingerichtet werden. Dazu sind folgende Schritte notwendig:

* Die PostgreSQL Version *alpine* wird innerhalb eines Containers installiert:

```sh
$ docker run --name postgres-customer-management-container -e POSTGRES_PASSWORD=customer-management -d -p 5433:5432 postgres:alpine
```

* Es muss eine neue Datenbank innerhalb des Containers angelegt:

```sh
docker exec -it postgres-customer-management-container /bin/bash
psql -U postgres
CREATE DATABASE customer_management;
```
