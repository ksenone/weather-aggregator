package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import model.Data
import model.openweathermap._
import model.worldweatheronline._

import service.WeatherAggregatorService

import play.api.libs.json._
import play.api.libs.ws._
import scala.concurrent.ExecutionContext

import scala.concurrent.Await
import scala.concurrent.duration.Duration


@Singleton
class WeatherAggregatorController @Inject()(service: WeatherAggregatorService, ws: WSClient, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  def getWeather(area: String) = Action.async { 
    service.reset

    val result = ws.url(s"https://api.openweathermap.org/data/2.5/weather?q=${area}&appid=78a458e8acabd8f34e1d5f7662e57583&units=metric&lang=ru").get().map { response =>

      val json = Json.parse(response.body)

      val weatherResult = json.validate[DataOWM]

      weatherResult match {
        case JsSuccess(weather, _) => service.addData(weather)
        case e: JsError => print(e.toString)
      }
    }

    Await.result(result, Duration.Inf)

    ws.url(s"http://api.worldweatheronline.com/premium/v1/weather.ashx?lang=ru&mca=no&fx=no&key=bb340a2d805f4936800162953221605&q=${area}&cc=yes&format=json").get().map { response =>

      val json = Json.parse(response.body)

      val weatherResult = json.validate[DataWWO]

      weatherResult match {
        case JsSuccess(weather, _) => service.addData(weather)
        case e: JsError => print(e.toString)
      }

      Redirect(routes.WeatherAggregatorController.showWeather)
    }
  }

  def showWeather = Action {
    Ok(views.html.weather(service.getWeatherOutput))
  }

  def showServices = Action {
    Ok(views.html.services(service.getServicesMap))
  }
}
