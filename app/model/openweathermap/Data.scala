package model.openweathermap

import play.api.libs.json._
import play.api.libs.functional.syntax._

import model.Data

case class DataOWM(temp: Double, city: String, weather: Seq[Weather], windSpeed: Double) extends Data
case class Weather(main: String, description: String)

object DataOWM {
  implicit val dataReads: Reads[DataOWM] = (
      (JsPath \ "main" \ "temp").read[Double] and
      (JsPath \ "name").read[String] and
      (JsPath \ "weather").read[Seq[Weather]] and 
      (JsPath \ "wind" \ "speed").read[Double]
    )(DataOWM.apply _)

  implicit val dataWrites: Writes[DataOWM] = (
      (JsPath \ "temp").write[Double] and
      (JsPath \ "name").write[String] and
      (JsPath \ "weather").write[Seq[Weather]] and
      (JsPath \ "wind" \ "speed").write[Double]
    )(unlift(DataOWM.unapply))
}

object Weather {
  implicit val weatherReads: Reads[Weather] = (
    (JsPath \ "main").read[String] and
    (JsPath \ "description").read[String]
  )(Weather.apply _)

  implicit val weatherWrites: Writes[Weather] = (
    (JsPath \ "main").write[String] and
    (JsPath \ "description").write[String]
  )(unlift(Weather.unapply))
  
}

