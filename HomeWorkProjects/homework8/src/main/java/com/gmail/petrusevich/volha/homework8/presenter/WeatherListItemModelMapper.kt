package com.gmail.petrusevich.volha.homework8.presenter

import com.gmail.petrusevich.volha.homework8.repository.WeatherDataModel

class WeatherListItemModelMapper : (List<WeatherDataModel>) -> List<WeatherListItemModel> {

    override fun invoke(weatherDataModelList: List<WeatherDataModel>): List<WeatherListItemModel> =
            weatherDataModelList.map { weatherDataModel ->
                WeatherListItemModel(
                        temperature = weatherDataModel.temperature,
                        description = weatherDataModel.description,
                        time = weatherDataModel.time,
                        urlIcon = weatherDataModel.urlIcon,
                        cityName = weatherDataModel.cityName
                )
            }
}