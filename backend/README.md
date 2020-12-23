## Dependencies

### Ribbon
* Ribbon is a Client-side Load Balancer that alternately calls the available Instances of the Services to be invoked in a Round-Robin-Process.

### Zipkin
* Zipkin tracks Requests between Services using a TraceID.
* docker run --name tracing-dashboard -d -p 8093:9411 openzipkin/zipkin

### Turbine
* Trubine allows Aggregation of Hystrix Streams. The Streams have the format: http://host:port/turbine.stream

### Gateway Service
* Gateway Service serves as the Facade of the System, so the individual Services are not visible to the Outside. In addition, the Gateway Service distributes the Security Token to gain Access to the Services.
___

## Abhängigkeiten

### Ribbon

* Ribbon ist ein Client-seitiger Load Balancer, der abwechselnd die zur Verfügung stehenden Instanzen des aufzurufenden Service nach dem Rundlauf-Verfahren aufruft.

### Zipkin
* Zipkin tracks requests between services using a TraceID
* docker run --name tracing-dashboard -d -p 8093:9411 openzipkin/zipkin

### Turbine
* Trubine ermöglicht die Aggregierung von Hystrix-Streams. Die Streams haben das Format: http://host:port/turbine.stream

### Gateway-Service
* Gateway-Service dient als Fassade des Systems, somit sind die einzelnen Services nach außen nicht sichtbar. Zusätzlich verteilt das Gateway-Service den Sicherheitstoken, um einen Zugang zu den Services zu erlangen.