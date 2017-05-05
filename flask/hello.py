from flask import Flask, render_template, request, url_for
app = Flask(__name__)
from kafka import KafkaProducer
from kafka.errors import KafkaError
import json

producer = KafkaProducer(bootstrap_servers=['localhost:9092'])

# Asynchronous by default
future = producer.send('test', b'raw_bytes')



@app.route('/')
def hello_world():
    producer.send('test', b'Hello, World!')
    return 'Hello, World!'

@app.route('/hello/', methods=['POST'])
def hello():
    print("Hits the request")
    #name=request.form['key']
    #print(request.data)
    #n = json.dumps(request.data)
    #print(n['key'])
    #producer.send('test', str(request.data).encode('utf-8'))
    producer.send('test', str("abc").encode('utf-8'))
    return "Success"



