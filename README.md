This service aimed to send and receive messages.

The API is organized around REST. API has endpoint:

- localhost:8094/message/send/{topic}

Endpoint accepts JSON request

User can provide message using POST request, the form of request body:
{
"messageText":"some String value"
}


To prepare Kafka: Open url https://kafka.apache.org/ and download Kafka
Run commands:
$ bin/zookeeper-server-start.sh config/zookeeper.properties
$ bin/kafka-server-start.sh config/server.properties