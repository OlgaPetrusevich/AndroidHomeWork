package com.gmail.petrusevich.volha.homework8.repository

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
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
                            time = timeMapper(getLong("dt")),
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
        return "$temperature C"
    }

    private fun timeMapper(date: Long): String {
        val format = "HH:mm"
        val dateTimeFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateTimeFormatter.format(date)
    }

    private fun urlToImageMapper(imageId: String): String {
        return "http://openweathermap.org/img/wn/$imageId@2x.png"
    }


}