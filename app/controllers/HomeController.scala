package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json.Json
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

final case class Name(id: String, name: String)

@Singleton
class HomeController @Inject() (val controllerComponents: ControllerComponents)
    extends BaseController {

  private val nameList = ListBuffer(
    Name("1", "Apple"),
    Name("2", "Orange"),
    Name("3", "Kiwi"),
    Name("4", "Watermelon"),
    Name("5", "Banana")
  )

  // Format in order to send as json obj
  implicit val nameFormat =
    Json.format[Name]

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def ping() = Action { implicit request: Request[AnyContent] =>
    Ok("App is running!")
  }

  def getAllNames() = Action { _ =>
    Ok(Json.obj("names" -> nameList))
  }

  def getName(id: String) = Action { _ =>
    var foundItem: Option[Name] = nameList.find(_.id == id)

    foundItem match {
      case Some(item) =>
        Ok(Json.obj("id" -> s"${item.id}", "name" -> s"${item.name}"))
      case None => NotFound
    }
  }

  // POST
  def addName() = Action.async { implicit request =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    jsonBody
      .map { json =>
        Future.successful(
          Ok(Json.obj("names" -> updateNameList(json)))
        )
      }
      .getOrElse {
        Future.successful(BadRequest("Expecting application/json request body"))
      }
  }

  // TODO: id needs to be unique, .length is not reliable
  private def updateNameList(json: JsValue) = {
    val nameString = (json \ "name").as[String]
    var newName =
      Name((nameList.length + 1).toString(), s"${nameString}")
    nameList += newName
  }
}
