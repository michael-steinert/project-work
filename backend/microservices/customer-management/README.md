## Setup

### Application Server:
* The compiled Microservice *Customer-Management* already contains the Application Server and can be executed directly inside a Container that has Java version 15 installed. The following Steps are necessary:

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

* The running Microservices is available at the URL http://localhost:8081.

### Database:

* The Microservice *Customer-Management* needs its own Database. Therefore an Instance of the PostgreSQL Database is set up on a Container. The following steps are necessary:

* The PostgreSQL Version *alpine* is installed inside the Container *postgres-customer-management-container*:

```sh
$ docker run --name postgres-customer-management-container -e POSTGRES_PASSWORD=customer-management -d -p 5433:5432 postgres:alpine
```

* A new Database is created within the Container *postgres-customer-management-container*:

```sh
docker exec -it postgres-customer-management-container /bin/bash
psql -U postgres
CREATE DATABASE customer_management;
```

### API of Microservices
| HTTP-Method | URL | Result |
| GET | /customerUid | returns the Customer with the corresponding customerUid |
| GET | /customers | returns all Customers of the System |
| POST | - | legt einen neuen Nutzer im System an |
| PUT | /customerUid | changes the Customer with the corresponding customerUid |
| DELETE | /customerUid | deletes the Customer with the corresponding customerUid |
___

## Einrichtung
### Anwendungsserver:

* Der kompilierte Microservice *Customer-Management* enthält bereist den Anwendungsserver und kann direkt innerhalb eines Containers ausgeführt werden, welcher die Java-Version 15 installiert hat. Dazu sind folgende Schritte notwendig:

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

* Der laufende Microservices ist erreichbar unter der URL http://localhost:8081.

### Datenbank:

* Der Microservice *Customer-Management* benötigt seine eigene Datenbank. Dafür wird auf einem Container eine Instanz der Datenbank PostgreSQL eingerichtet. Dazu sind folgende Schritte notwendig:

* Die PostgreSQL Version *alpine* wird innerhalb eines Containers *postgres-customer-management-container* installiert:

```sh
$ docker run --name postgres-customer-management-container -e POSTGRES_PASSWORD=customer-management -d -p 5433:5432 postgres:alpine
```

* Es wird eine neue Datenbank innerhalb des Containers *postgres-customer-management-container* angelegt:

```sh
docker exec -it postgres-customer-management-container /bin/bash
psql -U postgres
CREATE DATABASE customer_management;
```

### Schnittstelle des Microservices

| First Header  | Second Header |
| ------------- | ------------- |
| Content Cell  | Content Cell  |
| Content Cell  | Content Cell  |

| HTTP-Methode | URL | Ergebnis |
| GET | /customerUid | liefert den Kunden mit der entsprechenden customerUid |
| GET /customers | liefert alle Kunden des Systems |
| POST | - | legt einen neuen Kunden im System an |
| PUT | /customerUid | ändert den Kunden mit der entsprechenden customerUid |
| DELETE | /customerUid | löscht den Kunden mit der entsprechenden customerUid |
