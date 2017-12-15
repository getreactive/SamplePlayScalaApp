package messages

import play.api.libs.json.Json

/**
  * Created by rahulkumar on 05/03/17.
  */
case class UploadMessage(uuid:String,
                         filename: String,
                         size: Long,
                         startTime: Long,
                         endTime: Long,
                         totalUploadTime: Long,
                         path: String,
                         filetype: String)


object UploadMessage {
  implicit val jsonFormat = Json.format[UploadMessage]
}