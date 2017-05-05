# Sparkstreaming
Spark Streaming with Kafka using python
Install Spark
    brew install apache-spark
Install Kafka 
    brew install kafka
Install Maven
   brew install maven
   
   
Lets check kafka working first:

Create topic
kafka-create-topic --zookeeper localhost:2181 --replica 3 --partition 1 --topic test

List topic
kafka-list-topic --zookeeper localhost:2181

Start zookeeper
zkserver start

Start Kafka server 
kafka-server-start /usr/local/etc/kafka/server.properties

Start a terminal consumer
kafka-console-consumer --zookeeper localhost:2181 --topic test --from-beginning

Start a terminal Producer
kafka-console-producer --broker-list localhost:9092 --topic test

Type some messages in the producer terminal and check if it gets recieved at the consumer end.

Twitter data generation :
Auto generation of twitter messages



Spark Streaming :
spark-submit --packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.0.2 Sparkstreaming.py 

Flask :
export FLASK_APP=hello.py
flask run
