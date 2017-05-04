#    Spark
from pyspark import SparkContext
#    Spark Streaming
from pyspark.streaming import StreamingContext
#    Kafka
from pyspark.streaming.kafka import KafkaUtils
#    json parsing
import json

import requests

def check(tweet):
    if "@" in tweet:
        #resp = requests.get('http://127.0.0.1:5000/hello/')
        resp = requests.post('http://127.0.0.1:5000/hello/', data={'key':tweet})
        print(resp.content)

sc = SparkContext(appName="PythonSparkStreamingKafka_RM_01")
sc.setLogLevel("WARN")
ssc = StreamingContext(sc, 60)
kafkaStream = KafkaUtils.createStream(ssc, 'localhost:2181', 'spark-streaming', {'twitter':1})
parsed = kafkaStream.map(lambda v: check(v[1]))
parsed.count().map(lambda x:'Tweets in this batch: %s' % x).pprint()

ssc.start()
ssc.awaitTermination()
