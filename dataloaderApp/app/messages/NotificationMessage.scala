package messages

import play.api.libs.json.Json

/**
  * Created by rahulkumar on 05/03/17.
  */
case class NotificationMessage(messageId: String,
                               messageType: String,
                               source :String,
                               uploadedDate:String,
                               path:String,
                               senderId: String)

object NotificationMessage {
  implicit val notificationjsonFormat = Json.format[NotificationMessage]
}