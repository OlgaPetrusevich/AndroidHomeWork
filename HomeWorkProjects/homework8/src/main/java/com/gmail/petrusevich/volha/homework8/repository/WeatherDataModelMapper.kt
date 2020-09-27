package com.gmail.petrusevich.volha.homework8.repository

import org.json.JSONObject

class WeatherDataModelMapper : (String) -> List<WeatherDataModel> {

    override fun invoke(jsonData: String): List<WeatherDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonArray = jsonObject.getJSONArray("list")
        if (jsonArray.length() != 0) {
            val weatherList = mutableListOf<WeatherDataModel>()
            for (index in 0 until jsonArray.length()) {
                val weatherData = with(jsonArray.getJSONObject(index)) {
                    WeatherDataModel(
                            temperature = getString("temp"),
                            description = getString("description"),
                            time = getString("dt")
                    )
                }
                weatherList.add(weatherData)
            }
            return weatherList
        }
        return emptyList()
    }
}