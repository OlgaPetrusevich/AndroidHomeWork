package com.gmail.petrusevich.volha.homework8.city.domain

import android.content.Context
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import com.gmail.petrusevich.volha.homework8.city.repository.CityListRepository
import com.gmail.petrusevich.volha.homework8.city.repository.CityListRepositoryImpl
import io.reactivex.Observable

class CityListUseCaseImpl(
        context: Context
) : CityListUseCase {

    private val repository: CityListRepository = CityListRepositoryImpl(context)

    override fun getCityList(): Observable<List<CityDomainModel>> =
            repository.getCityList()

    override fun insertCity(cityDataModel: CityDataModel) {
        repository.insertCity(cityDataModel)
    }


}