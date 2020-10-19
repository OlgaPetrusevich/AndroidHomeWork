package com.gmail.petrusevich.volha.homework8.weather.fragmentsweather

import com.gmail.petrusevich.volha.homework8.weather.presenter.WeatherListItemModel

interface WeatherListView {

    fun showWeatherList(weatherList: List<WeatherListItemModel>)
    fun onError(errorMessage: String)
}