package com.gmail.petrusevich.volha.homework8.city.datasource

import android.content.Context
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import com.gmail.petrusevich.volha.homework8.city.datasource.database.CityListDatabase
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

class DatabaseCityDataSource(
        context: Context
) : CityListDataSource {

    private val cityListDao = CityListDatabase.getInstance(context)?.getCityListDao()

    override fun getCityList(): Observable<List<CityDataModel>> {
        return Observable.create(ObservableOnSubscribe<List<CityDataModel>>() {
            val cityList = cityListDao?.getCityList()
            if (cityList != null) {
                it.onNext(cityList)
            } else {
                it.onNext(emptyList())
            }
        }).subscribeOn(Schedulers.computation())
    }

    override fun insertCity(cityDataModel: CityDataModel) {
        cityListDao?.insertCity(cityDataModel)
    }
}