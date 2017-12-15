package com.app.consumer

import java.io.FileInputStream
import java.util.{Collections, Properties}

import com.app.messages.UploadMessage
import com.app.services.{DBStoreService, FileTransferService}
import com.typesafe.config.ConfigFactory
import org.apache.commons.net.ftp.FTPClient
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.errors.WakeupException
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsValue, Json, _}

import scala.collection.JavaConversions._

/**
  * Created by rahulkumar on 06/03/17.
  */
class ConsumerApp(_id:Int,_groupId:String,_topics:String) extends Runnable {

  implicit val UploadMessageReads: Reads[UploadMessage] = (
    (JsPath \ "uuid").read[String] and
      (JsPath \ "filename").read[String] and
      (JsPath \ "size").read[Long] and
      (JsPath \ "startTime").read[Long] and
      (JsPath \ "endTime").read[Long] and
      (JsPath \ "totalUploadTime").read[Long] and
      (JsPath \ "path").read[String] and
      (JsPath \ "filetype").read[String]
    )(UploadMessage.apply _)
  val config = ConfigFactory.load()
  val client:FTPClient  = new FTPClient()
  var fis:FileInputStream = null
  val id:Int = _id
  var groupId:String = _groupId
  var topics:String = _topics
  val props: Properties = new Properties()


  props.put("bootstrap.servers", config.getString("kafka.bootstrap.servers"))
  props.put("group.id", groupId)
  props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  var consumer = new KafkaConsumer[String, String](props)

  def getConsumer(): KafkaConsumer[String,String] = {
    consumer
  }

  def run() = {
    try{
      consumer.subscribe(Collections.singletonList(topics))
      while (true) {
        val records = consumer.poll(Long.MaxValue)
        for (record <- records) {
          println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
          val message = record.value()
          val parsedValue:JsValue = Json.parse(message)
          val msgRes = parsedValue.validate[UploadMessage]
          msgRes match {
            case s: JsSuccess[UploadMessage] => {
              val filename = s.get.filename
              val path = s.get.path
              //TODO: Need to update Metadata DB
              FileTransferService.uploadFile(path,filename)
              DBStoreService.loadData(path)
            }
            case e: JsError => println("Errors: " + JsError.toJson(e).toString())
          }
          }
      }
    }catch {
      case we:WakeupException => we.printStackTrace()
    }finally {
      consumer.close()
    }
  }
  def shutdown() {
    consumer.wakeup()
  }
}
