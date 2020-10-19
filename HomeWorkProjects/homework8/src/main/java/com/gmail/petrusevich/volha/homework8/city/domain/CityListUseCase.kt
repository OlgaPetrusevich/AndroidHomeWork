package com.gmail.petrusevich.volha.homework8.city.domain

import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import io.reactivex.Observable

interface CityListUseCase {

    fun getCityList(): Observable<List<CityDomainModel>>

    fun insertCity(cityDataModel: CityDataModel)
}