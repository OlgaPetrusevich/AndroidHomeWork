package com.gmail.petrusevich.volha.homework8.fragmentsweather

import android.os.Message
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListItemModel

interface WeatherListView {

    fun showWeatherList(weatherList: List<WeatherListItemModel>)
    fun onError(errorMessage: String)
}