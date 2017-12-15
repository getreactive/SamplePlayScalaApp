package controllers

import javax.inject._

import com.app.producer.ProducerAPP
import play.api.Configuration
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.mvc.{Action, Controller}
import services.UploadService

/**
  * Created by rahulkumar on 05/03/17.
  */
@Singleton
class MainController @Inject() (val uploadService: UploadService, val configuration: Configuration) extends Controller {

  def dataUploader = Action.async(parse.multipartFormData) { request =>
    val futureResult = scala.concurrent.Future { uploadService.upload(request) }
    futureResult.map(res => {
      if(res.info != "error"){
        val id = res.uploadmessage.get.uuid
        val message = Json.toJson(res.uploadmessage)
        val p = new ProducerAPP
        p.fileServerJob(configuration.getString("kafka.topic").get,id,message.toString())
      }

      Ok(Json.toJson(res))
    })
  }

}
