package services

import messages.AppMessage
import play.api.libs.Files
import play.api.mvc.{MultipartFormData, Request}

/**
  * Created by rahulkumar on 04/03/17.
  */
trait UploadService {

  /**
    *
    * @param request
    * @return
    */

  def upload(request: Request[MultipartFormData[Files.TemporaryFile]]): AppMessage

  def notifyWorker() : String
}
