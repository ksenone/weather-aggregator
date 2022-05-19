package service

import javax.inject._

import collection.mutable

import model.Data
import model.openweathermap._
import model.worldweatheronline._

import math.BigDecimal


@Singleton
class WeatherAggregatorService @Inject()() {

  private val services = Map("openweathermap.org" -> "доступен", "worldweatheronline.com" -> "доступен")
  private var weatherData = mutable.ListBuffer.empty[Data]

  def reset: Unit = 
    weatherData = mutable.ListBuffer.empty[Data]

  def addData(data: Data): Unit = 
    weatherData += data
  
  def getWeatherList: mutable.ListBuffer[Data] = weatherData

  def getWeatherOutput: mutable.ListBuffer[String] = 
    weatherData.map { data =>
    data match {
      case d: DataOWM => s"OpenWeatherMap: ${d.city} ---> ${d.temp.toInt}°C, ${d.weather.head.description}, скорость ветра - ${d.windSpeed} м/с"
      case d: DataWWO => {
        val windSpeedMs = BigDecimal(d.weather.head.windSpeed.toDouble / 3.6).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
        s"LocalWeather: ${d.area.head.area} ---> ${d.weather.head.temp}°C,  ${d.weather.head.description.head.description.toLowerCase}, скорость ветра - ${windSpeedMs} м/с"
      }
    }

  }

  def getServicesMap: Map[String, String] = services
}