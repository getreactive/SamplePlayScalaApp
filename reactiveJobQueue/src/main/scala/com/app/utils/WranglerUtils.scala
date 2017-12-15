package com.app.utils

import com.app.messages.User
import org.joda.time.format.DateTimeFormat

import scala.collection.mutable
import scala.io.Source

/**
  * Created by rahulkumar on 06/03/17.
  */
object WranglerUtils {

  def convertToUTC(date: String): String = {
    var res = ""
    try{
      val dtf = DateTimeFormat.forPattern("dd-MM-YY HH:mm:ss")
      val jodatime = dtf.parseDateTime(date).toDateTime( org.joda.time.DateTimeZone.UTC)
      res = jodatime.toString
    }catch{
      case e: IllegalArgumentException => {
        res = "70-01-01T00:00:00.000Z"
      }
    }
    res
  }

  def cleanData(line: String): Option[User] = {
    line.startsWith("Id,name,time_of_start,Obs.,,") match {
      case true => None
      case false => {
        val splitline = line.split(",").toList
        splitline match {
          case _ :: "You Have Completed the Test" :: _ => None
          case id :: name :: time_of_start :: _ =>
            Some(User(id, name.toLowerCase, convertToUTC(time_of_start)))
          case _ => None
        }
      }
    }
  }

  def processFile(source: Source):Boolean = {

    val mappy = mutable.HashMap.empty[String, User]

    for (line <- source.getLines()) {
      cleanData(line).foreach(user => {
        mappy.put(user.Id, user)
      })
    }
    DBUtils.saveToDB(mappy)
  }

  def run(path: String): Boolean = {
    val source = Source.fromFile(path)
    processFile(source)
  }

}
