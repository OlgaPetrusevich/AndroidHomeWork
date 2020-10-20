package com.gmail.petrusevich.volha.homework8.weather.presenter

interface WeatherListPresenter {

    fun fetchWeatherList(units: String, city: String)
    fun dispose()
}