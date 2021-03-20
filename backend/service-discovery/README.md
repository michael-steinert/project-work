## Service Discovery

* The Service Discovery registers each individual Microservice.

* The running Service Discovery is available at the URL http://localhost:8761.
___

## Service-Discovery

* Die Service-Discovery registriert jeden einzelnen Microservice.

* Der laufende Konfigurations-Server ist erreichbar unter der URL http://localhost:8761.
___

### Jib Maven Plugin
*Jib is a deamonless Maven Plugin for building Docker and OCI Images for Java Applications.*

* Build an Image with Jib and store it locally
  * docker login
  * mvnw clean install -P jib-push-to-local

* Build an Image with Jib and push it to Docker Hub
  * docker login
  * mvnw clean install -P jib-push-to-dockerhub