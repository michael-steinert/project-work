Start the elk-Stack:
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

* Select "Stack Management" then "Index Patterns" and create Index with "share-your-idea-*"