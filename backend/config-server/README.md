## Configuration Server

* The Configuration Server provides the Configurations to each individual Microservice.

* The running Configuration Server is available at the URL http://localhost:8888.

* The Configurations are available at the following URLs:

	* http://localhost:8888/customer-management-service/default
	
	* http://localhost:8888/sales-department-service/default
	
	* http://localhost:8888/manufacturing-department-service/default
___

## Konfigurations-Server

* Der Konfigurations-Server stellt die Konfigurationen für jeden einzelnen Microservice zur Verfügung.

* Der laufende Konfigurations-Server ist erreichbar unter der URL http://localhost:8888.

* Die Konfigurationen sind unter folgenden URLs verfügbar:

	* http://localhost:8888/customer-management-service/default
	
	* http://localhost:8888/sales-department-service/default
	
	* http://localhost:8888/manufacturing-department-service/default
___

### Jib Maven Plugin
*Jib is a deamonless Maven Plugin for building Docker and OCI Images for Java Applications.*

* Build an Image with Jib and store it locally
	* docker login
	* mvnw clean install -P jib-push-to-local

* Build an Image with Jib and push it to Docker Hub
	* docker login
	* mvnw clean install -P jib-push-to-dockerhub
