package com.gmail.petrusevich.volha.homework8.weather.repository

import io.reactivex.Single

interface WeatherRepository {

    fun getHourlyWeatherList(units: String, city: String): Single<List<WeatherDataModel>>

}