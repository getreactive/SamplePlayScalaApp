package com.app.utils

import java.sql.{Connection, DriverManager, SQLException}

import com.app.messages.User
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

/**
  * Created by rahulkumar on 06/03/17.
  */
object DBUtils {

  def getConnection:Connection = {
    val config = ConfigFactory.load()
    val driver = config.getString("datadb.driver")
    val url = config.getString("datadb.url")
    val username = config.getString("datadb.user")
    val password = config.getString("datadb.pass")
    var connection:Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
    } catch {
      case e:Exception => {
       println(e.printStackTrace)
      }
    }
    connection
  }

  def saveToDB(m: mutable.HashMap[String, User]):Boolean = {

    val connection = getConnection
    try {
      for ((k,v) <- m) {
        printf("key: %s, value: %s\n", k, v)
        val statement = connection.createStatement()
        val sql = "REPLACE into userinfo (id, name, time_of_start) values(\""+v.Id+"\",\""+v.name+"\",\""+v.time_of_start+"\")"
        val res = statement.executeQuery(sql)
      }
    } catch {
      case e:Exception => {
        println(e.printStackTrace)
      }
    }
    connection.close()
    println(m)
    true
  }

}
