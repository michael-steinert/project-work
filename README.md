# Project Work

*The Goal of this Project Work is to achieve the academic Degree Master of Science in Computer Science.*

* The Goal of the Application is to provide a Platform for People to work collaboratively on shared Ideas.

* The Application is based on a Microservice Architecture and the Zoom Interface to enable Users to work well together.

## Description of the System

The System consists of fourth [Microservices](backend/microservice), consisting of a Backend, Frontend and Datastorage, a server for Service Discovery, a Gateway Service for dynamic Routing and a Server for the Configuration of the individual [Microservices](backend/microservice).

![System Overview](https://user-images.githubusercontent.com/29623199/107240929-c4ed7880-6a2a-11eb-8215-634ffa87c1dc.png)

## Choice of technology

* The [Service Discovery](backend/service-discovery) is realized with the help of Eureka on the individual Microservices. A Service Discovery ensures that the individual Microservices can find each other. Due to its dynamic Nature, it allows scaling the Application by adding individual Microservices.

* The [Gateway Service](backend/gateway-service) is realized with the help of Zuul. A Gateway Service allows the Routing to be dynamic, serving as an Endpoint for the individual Microservices.

* The [Configuration Server](backend/configuration-server) for the Configuration is realized with the help of Configuration Server. A Configuration Server facilitates the Storage and Transfer of Configuration Data to the individual Microservices. Spring Cloud Config has a REST-API that provides Configuration Data from a local GIT-Repository.

* The Backend of the individual [Microservices](backend/microservice) is realized with the help of the Spring Framework, especially Spring Boot and Spring Cloud.

* The Frontend of the individual [Microservices](backend/microservice) is realized with the help of the React Framework and the Framework Material-UI.

* Each of these [Microservice](backend/microservices) has its own Data Management, which is realized with the help of PostgreSQL.

## Overview of the Entities
![Entities Overview](https://user-images.githubusercontent.com/29623199/103104524-159a4500-4628-11eb-8a93-7dbbda112b97.png)
___

# Projektarbeit

*Ziel dieser Projektarbeit ist es, den akademischen Grad Master of Science in der Informatik zu erreichen.*

* Ziel der Anwendung ist es eine Plattform für Menschen bereitzustellen, die eine kollaborative Arbeit an gemeinsamen Ideen zu ermöglichen.

* Die Anwendung basiert auf einer Microservice-Architektur und der Zoom-Schnittstelle, um den Nutzern eine gute Zusammenarbeit zu ermöglichen.

## Beschreibung des Systems

Das System besteht aus vier [Microservices](backend/microservice), welche aus einem Backend, Frontend und einer Datenhaltung bestehen, einem Server für die Service-Discovery, einem Gateway-Service für das dynamische Routing und einem Server für die Konfiguration der einzelnen Microservices.

![System Overview](https://user-images.githubusercontent.com/29623199/107240929-c4ed7880-6a2a-11eb-8215-634ffa87c1dc.png)

## Wahl der Technologien

* Die [Service-Discovery](backend/service-discovery) wird mit Hilfe von Eureka auf den einzelnen Microservices realisiert. Eine Service-Discovery stellt sicher, dass sich die einzelnen Microservices gegenseitig finden können. Durch ihre dynamische Art, erlaubt sie das Skalieren der Anwendung durch die Hinzunahme einzelner Microservices.

* Der [Gateway-Service](backend/gateway-service) wird mit Hilfe von Zuul realisiert. Ein Gateway-Service ermöglicht das Routing dynamisch zu gestalten, und somit den einzelnen Microservices als ein Endpunkt zu dienen.

* Der [Konfigurations-Server](backend/configuration-server) für die Konfiguration wird mit Hilfe von Konfigurations-Server realisiert. Ein Konfigurations-Server erleichtert das Speichern und Übergeben der Konfigurationsdaten an die einzelnen Microservice. Spring Cloud Config hat eine REST-API, die die Konfigurationsdaten aus einem lokalen Git-Repository zur Verfügung stellt.

* Das Backend der einzelnen [Microservices](backend/microservice) wird mit Hilfe des Spring Frameworks insbesondere mit Spring Boot und Spring Cloud realisiert.

* Das Frontend der einzelnen [Microservices](backend/microservice) wird mit Hilfe des React Framework und dem Framework Material-UI realisiert.

* Jeder dieser [Microservices](backend/microservice) verfügt über eine eigene Datenhaltung, welche mit Hilfe von PostgreSQL realisiert wird.

## Überblick über der Entitäten
![Entities Overview](https://user-images.githubusercontent.com/29623199/103104524-159a4500-4628-11eb-8a93-7dbbda112b97.png)