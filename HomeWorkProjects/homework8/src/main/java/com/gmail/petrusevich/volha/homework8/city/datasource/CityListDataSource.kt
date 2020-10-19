package com.gmail.petrusevich.volha.homework8.city.datasource

import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import io.reactivex.Observable

interface CityListDataSource {

    fun getCityList(): Observable<List<CityDataModel>>

    fun insertCity(cityDataModel: CityDataModel)
}