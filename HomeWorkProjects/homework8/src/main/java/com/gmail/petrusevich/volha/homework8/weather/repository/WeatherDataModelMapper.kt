package com.gmail.petrusevich.volha.homework8.weather.repository

import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

private const val JSON_ARRAY_NAME = "list"

class WeatherDataModelMapper : (String) -> List<WeatherDataModel> {

    override fun invoke(jsonData: String): List<WeatherDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonArray = jsonObject.getJSONArray(JSON_ARRAY_NAME)
        if (jsonArray.length() != 0) {
            val weatherList = mutableListOf<WeatherDataModel>()
            for (index in 0 until jsonArray.length()) {
                val weatherData = with(jsonArray.getJSONObject(index)) {
                    WeatherDataModel(
                            temperature = temperatureMapper(getJSONObject("main").getString("temp")),
                            description = getJSONArray("weather").getJSONObject(0).getString("description"),
                            time = timeMapper(getString("dt_txt")),
                            urlIcon = urlToImageMapper(getJSONArray("weather").getJSONObject(0).getString("icon")),
                            cityName = jsonObject.getJSONObject("city").getString("name")
                    )
                }
                weatherList.add(weatherData)
            }
            return weatherList
        }
        return emptyList()
    }

    private fun temperatureMapper(temp: String): String {
        val temperature = temp.toDouble().roundToInt()
        return "$temperature"
    }

    private fun timeMapper(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(date, formatter)
        return "${dateTime.hour}:${dateTime.minute}"
    }

    private fun urlToImageMapper(imageId: String): String {
        return "http://openweathermap.org/img/wn/$imageId@2x.png"
    }


}