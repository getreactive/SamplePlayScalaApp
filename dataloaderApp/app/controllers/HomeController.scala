package controllers

import java.io.File
import javax.inject._

import com.app.producer.ProducerAPP
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (val webJarAssets: WebJarAssets, val configuration: Configuration) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index(webJarAssets))
  }

  def view(template: String) = Action { implicit request =>
    template match {
      case "home" => Ok(views.html.home())
      case _ => NotFound
    }
  }

}
