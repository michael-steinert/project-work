## Gateway Service

* The Gateway Service provides a Facade for Requests to the Application.

* The running Gateway Service is available at the URL http://localhost:8080.
___

## Gateway-Service

* Der Gateway-Service stellt eine Fassade für Anfragen an die Anwendung dar.
  
* Der laufende Gateway-Service ist erreichbar unter der URL http://localhost:8080.
___

### Jib Maven Plugin
*Jib is a deamonless Maven Plugin for building Docker and OCI Images for Java Applications.*

* Build an Image with Jib and store it locally
	* docker login
	* mvnw clean install -P jib-push-to-local

* Build an Image with Jib and push it to Docker Hub
	* docker login
	* mvnw clean install -P jib-push-to-dockerhub
