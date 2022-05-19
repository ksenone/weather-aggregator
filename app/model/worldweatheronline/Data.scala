package model.worldweatheronline

import play.api.libs.json._
import play.api.libs.functional.syntax._

import model.Data

case class DataWWO(area: Seq[Request], weather: Seq[CurrentCondition]) extends Data
case class Request(areaType: String, area: String)
case class CurrentCondition(temp: String, description: Seq[WeatherDescription], windSpeed: String)
case class WeatherDescription(description: String)

object DataWWO {
  implicit val dataReads: Reads[DataWWO] = (
      (JsPath \ "data" \ "request").read[Seq[Request]] and
      (JsPath \ "data" \ "current_condition").read[Seq[CurrentCondition]]
    )(DataWWO.apply _)

  implicit val dataWrites: Writes[DataWWO] = (
      (JsPath \ "data" \ "request").write[Seq[Request]] and
      (JsPath \ "data" \ "current_condition").write[Seq[CurrentCondition]]
    )(unlift(DataWWO.unapply))
}

object Request {
  implicit val dataReads: Reads[Request] = (
      (JsPath \ "type").read[String] and
      (JsPath \ "query").read[String]
  )(Request.apply _)

  implicit val dataWrites: Writes[Request] = (
      (JsPath \ "type").write[String] and
      (JsPath \ "query").write[String]
    )(unlift(Request.unapply))
}

object WeatherDescription {
  implicit val descriptionReads: Reads[WeatherDescription] = 
    (JsPath \ "value").read[String].map(WeatherDescription(_))

  implicit val descriptionWrites: Writes[WeatherDescription] = 
    (JsPath \ "value").write[String].contramap(_.description)
}

object CurrentCondition {
  implicit val weatherReads: Reads[CurrentCondition] = (
    (JsPath \ "temp_C").read[String] and
    (JsPath \ "lang_ru").read[Seq[WeatherDescription]] and
    (JsPath \ "windspeedKmph").read[String]
  )(CurrentCondition.apply _)

  implicit val weatherWrites: Writes[CurrentCondition] = (
    (JsPath \ "temp_C").write[String] and
    (JsPath \ "lang_ru").write[Seq[WeatherDescription]] and
    (JsPath \ "windspeedKmph").write[String]
  )(unlift(CurrentCondition.unapply))
  
}