package com.app.messages

import play.api.libs.json.Json

/**
  * Created by rahulkumar on 07/03/17.
  */
case class User(Id: String, name: String, time_of_start: String)

object User {
  implicit val jsonFormat = Json.format[User]
}


