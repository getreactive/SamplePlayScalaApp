package com.app.producer

import java.util.Properties

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, Producer, ProducerRecord}

/**
  * Created by rahulkumar on 04/03/17.
  */
class ProducerAPP {

  def fileServerJob(topicName:String, key:String, value: String): Unit = {
    val props: Properties = new Properties()
    val config = ConfigFactory.load()
    props.put("bootstrap.servers", config.getString("kafka.bootstrap.servers"))
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
    val producer: Producer[String,String] = new KafkaProducer(props)
    val record: ProducerRecord[String,String] = new ProducerRecord(topicName,key,value)
    producer.send(record)
    producer.close()
  }
}
