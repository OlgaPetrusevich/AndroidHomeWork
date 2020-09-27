package com.gmail.petrusevich.volha.homework8.repository

import io.reactivex.Single

interface WeatherRepository {

    fun getHourlyWeatherList(city: String): Single<List<WeatherDataModel>>

}