package services

import java.io.File
import java.util.UUID

import messages.{AppMessage, UploadMessage}
import play.api.libs.Files
import play.api.libs.Files.TemporaryFile
import play.api.mvc.{MultipartFormData, Request}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by rahulkumar on 05/03/17.
  */
class UploadServiceImpl extends UploadService {
  /**
    *
    * @param request
    * @return
    */
  def upload(request: Request[MultipartFormData[Files.TemporaryFile]]): AppMessage = {

    val id: String  = UUID.randomUUID.toString

    request.body.file("csvdata").map { file =>
      val startTime = System.currentTimeMillis
      import java.io.File
      val filename = file.filename
      val contentType = file.contentType
      val filesize = file.ref.file.length()
      println("File Name :"+filename+" "+contentType)
      val path = "/tmp/"+filename
      file.ref.moveTo(new File(path))
      val endTime = System.currentTimeMillis
      val totalTime = endTime - startTime
      val uploadmsg = UploadMessage(id,filename,filesize,startTime,endTime,totalTime,path,contentType.getOrElse(""))
      val appmsg = AppMessage("info",Some(uploadmsg))
      appmsg
    }.getOrElse {
      val appmsg = AppMessage("error",None)
      appmsg
    }
  }

  override def notifyWorker(): String = {
    ""
  }
}
