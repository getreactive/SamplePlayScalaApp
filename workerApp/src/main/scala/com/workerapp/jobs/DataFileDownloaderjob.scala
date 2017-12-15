package com.workerapp.jobs

import java.util
import java.util.concurrent.{Executors, TimeUnit}

import com.app.consumer.ConsumerApp
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

/**
  * Created by rahulkumar on 06/03/17.
  */
object DataFileDownloaderjob {

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    val numConsumers =config.getInt("kafka.numConsumers")
    val groupId = config.getString("kafka.groupId")
    val topic = config.getString("kafka.topic")
    val executor = Executors.newFixedThreadPool(numConsumers)
    val consumers:util.ArrayList[ConsumerApp] = new util.ArrayList[ConsumerApp]()

    for (i <- 1 to numConsumers) {
      val consumer:ConsumerApp = new ConsumerApp(i,groupId, topic)
      consumers.add(consumer)
      executor.submit(consumer)
    }

    Runtime.getRuntime.addShutdownHook(new Thread(){
      override def run(): Unit ={
        for (consumer <- consumers.asScala) {
          consumer.shutdown()
        }
        executor.shutdown()
        try {
          executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch {
          case e:InterruptedException => e.printStackTrace()
        }
      }
    })
  }

}
