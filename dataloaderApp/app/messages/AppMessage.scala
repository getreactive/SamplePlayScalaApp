package messages

import play.api.libs.json.Json

/**
  * Created by rahulkumar on 05/03/17.
  */
case class AppMessage(info: String, uploadmessage: Option[UploadMessage])

object AppMessage {
  implicit val appMessagejsonFormat = Json.format[AppMessage]
}