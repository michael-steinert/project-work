## Overview
* The Microservices generate their Logs.
* The Logs are read from the logstash.
* Then, Logstash converts the read Logs into JSON Format and indexes them into the Elasticsearch Cluster.
* The cluster is provided by Elasticsearch.
* Kibana is responsible for Visualizing the Clusters provided by Elasticsearch.

![ELK Stack](https://user-images.githubusercontent.com/29623199/103419600-11c07280-4b94-11eb-81e7-effefdc57ccc.png)

## Start the elk-Stack:

### Logstash
1. Create logstash.conf and save it in logstash/bin/
1. Run Logstash in CMD: logstash/bin/logstash.bat -f logstash.conf

* Show Logstash-Indices: http://localhost:9200/_cat/indices
* Show Logstash-Index: http://localhost:9200/logstash-index-name/_search

### Elasticsearch
1. Run Elasticsearch in CMD: elasticsearch/bin/elasticsearch.bat

### Kibana
1. Enable the Elasticsearch Host in kibana.yml
1. Run Kibana in CMD: kibana/bin/kibana.bat 

* Select "Stack Management" then "Index Patterns" and create Index with the Name "share-your-idea-*"
___

## Überblick
* Die Microservices generieren deren Logs.
* Die Logs werden vom Logstash ausgelesen.
* Anschließend werden die ausgelesenen Logs von Logstash in das Format JSON überführt und in das Elasticsearch-Cluster indexiert.
* Das Cluster wird von Elasticsearch zur Verfügung gestellt.
* Kibana ist zuständig für die Visualisierung der Cluster, welche von Elasticsearch bereitgestellt werden.

![ELK Stack](https://user-images.githubusercontent.com/29623199/103419600-11c07280-4b94-11eb-81e7-effefdc57ccc.png)

## Start des ELK-Stacks:

### Logstash
1. Erstellen der logstash.conf im Verzeichnis logstash/bin/
1. Ausführen von Logstash mit Hilfe des CMD: logstash/bin/logstash.bat -f logstash.conf

* Anzeigen der Logstash-Indizes: http://localhost:9200/_cat/indices
* Anzeigen eines Logstash-Indexes: http://localhost:9200/logstash-index-name/_search

### Elastisearch
1. Ausführen von Elasticsearch mit Hilfe der CMD: elasticsearch/bin/elasticsearch.bat

### Kibana
1. Den Elasticsearch Host in der kibana.yml erlauben
1. Ausführen von Kibana mit Hilfe der CMD: kibana/bin/kibana.bat 

* Auswählen "Stack Management" dann "Index Patterns" und einen Index mit dem Namen "share-your-idea-*" erstellen