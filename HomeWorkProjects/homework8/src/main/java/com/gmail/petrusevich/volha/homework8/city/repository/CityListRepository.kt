package com.gmail.petrusevich.volha.homework8.city.repository

import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import com.gmail.petrusevich.volha.homework8.city.domain.CityDomainModel
import io.reactivex.Observable

interface CityListRepository {

    fun getCityList(): Observable<List<CityDomainModel>>

    fun insertCity(cityDataModel: CityDataModel)
}