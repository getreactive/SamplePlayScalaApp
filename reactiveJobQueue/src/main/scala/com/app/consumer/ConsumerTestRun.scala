package com.app.consumer

import java.util
import java.util.concurrent.{Executors, TimeUnit}
import scala.collection.JavaConverters._
/**
  * Created by rahulkumar on 06/03/17.
  */
object ConsumerTestRun {

  def main(args: Array[String]): Unit = {
    val numConsumers = 3
    val groupId = "datarunner"
    val topic = "uploadmessage"
    val executor = Executors.newFixedThreadPool(numConsumers)
    val consumers:util.ArrayList[ConsumerApp] = new util.ArrayList[ConsumerApp]()

    for (i <- 1 to numConsumers) {
      val consumer:ConsumerApp = new ConsumerApp(i, groupId, topic)
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
