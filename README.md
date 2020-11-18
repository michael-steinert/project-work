# Project Work

*The goal of this project work is to achieve the academic degree Master of Science in Computer Science.*

## Description of the System

The System consists of three Microservices, consisting of a Backend, Frontend and Datastorage, a server for Service-Discovery and a Server for the Configuration of the individual Microservices.

![cover](https://user-images.githubusercontent.com/29623199/99527045-7638c100-299c-11eb-8039-1526bafe157d.png)

## Choice of technology

* Service discovery on the server is realized with the help of Eureka and on the individual microservices with the help of Ribbon.

* The server for the configuration is realized with the help of Config Server.

* The backend of the individual microservices is realized with the help of the Spring Framework, especially Spring Boot and Spring Cloud.

* The frontend of the individual microservices is realized with the help of the React Framework and the framework Material-UI.

* Each of these microservices has its own data management, which is realized with the help of PostgreSQL.

## Setup

### Application-Server:
* The compiled microservices already contain their application servers and can be executed directly inside a container that has Java version 15 installed. The following steps are necessary:

`
FROM openjdk:7
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac Main.java
CMD ["java", "Main"]
`

`
$ docker build -t my-java-app .
$ docker run -it --rm --name my-running-app my-java-app
`

### Database:

* Every single Microservice needs its own Database. Therefore an instance of the PostgreSQL Database must be created on a Container. This requires the following steps, which are explained using the Customer-Management Microservice as an example:

* The PostgreSQL version alpine is installed inside a container:

`
$ docker run --name postgres-customer-management-container -e POSTGRES_PASSWORD=customer-management -d -p 5433:5432 postgres:alpine
`

* A new Database must be created within the Container:

`
docker exec -it postgres-customer-management-container /bin/bash
psql -U postgres
CREATE DATABASE customer_management;
`

___

# Projektarbeit

*Ziel dieser Projektarbeit ist es, den akademischen Grad Master of Science in der Informatik zu erreichen.*

## Beschreibung des Systems

Das System besteht aus drei Microservices, welche aus einem Backend, Frontend und einer Datenhaltung bestehen, einem Server für die Service-Discovery und einem Server für die Konfiguration der einzelnen Microservices.

## Wahl der Technologien

* Die Service-Discovery auf dem Server wird mit Hilfe von Eureka und auf den einzelnen Microservices mit Hilfe von Ribbon realisiert.

* Der Server für die Konfiguration wird mit Hilfe von Config Server realisiert.

* Das Backend der einzelnen Microservices wird mit Hilfe des Spring Frameworks insbesondere mit Spring Boot und Spring Cloud realisiert.

* Das Frontend der einzelnen Microservices wird mit Hilfe des React Framework und dem Framework Material-UI realisiert.

* Jeder dieser Microservices verfügt über eine eigene Datenhaltung, welche mit Hilfe von PostgreSQL realisiert wird.

## Einrichtung
### Anwendungsserver:

Die kompilierten Microservices enthalten bereist deren Anwendungsserver und können direkt innerhalb eines Containers ausgeführt werden, welcher die Java-Version 15 installiert hat. Das sind folgende Schritte notwendig:

`
FROM openjdk:7
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac Main.java
CMD ["java", "Main"]
`

`
$ docker build -t my-java-app .
$ docker run -it --rm --name my-running-app my-java-app
`

### Datenbank:

* Jeder einzelne Microservice benötigt seine eigene Datenbank. Dafür muss auf einem Container eine Instanz der Datenbank PostgreSQL eingerichtet werden. Dazu sind folgende Schritte, welche beispielhaft an dem Customer-Management-Microservice erklärt werden, notwendig:

* Die PostgreSQL Version alpine wird innerhalb eines Containers installiert:

`
$ docker run --name postgres-customer-management-container -e POSTGRES_PASSWORD=customer-management -d -p 5433:5432 postgres:alpine
`

* Es muss eine neue Datenbank innerhalb des Containers angelegt:

`
docker exec -it postgres-customer-management-container /bin/bash
psql -U postgres
CREATE DATABASE customer_management;
`
