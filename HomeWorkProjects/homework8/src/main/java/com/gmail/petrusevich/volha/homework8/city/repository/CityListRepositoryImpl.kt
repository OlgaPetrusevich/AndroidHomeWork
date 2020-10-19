package com.gmail.petrusevich.volha.homework8.city.repository

import android.content.Context
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import com.gmail.petrusevich.volha.homework8.city.datasource.CityListDataSource
import com.gmail.petrusevich.volha.homework8.city.datasource.DatabaseCityDataSource
import com.gmail.petrusevich.volha.homework8.city.domain.CityDomainModel
import com.gmail.petrusevich.volha.homework8.city.domain.CityDomainModelMapper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CityListRepositoryImpl(
        context: Context
) : CityListRepository {

    private val cityListDataSource: CityListDataSource = DatabaseCityDataSource(context)
    private val cityDomainModelMapper: (List<CityDataModel>) -> List<CityDomainModel> = CityDomainModelMapper()

    override fun getCityList(): Observable<List<CityDomainModel>> =
            cityListDataSource.getCityList()
                    .subscribeOn(Schedulers.computation())
                    .map { list -> cityDomainModelMapper(list) }

    override fun insertCity(cityDataModel: CityDataModel) {
        cityListDataSource.insertCity(cityDataModel)
    }
}
